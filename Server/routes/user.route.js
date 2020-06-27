const auth = require("../middleware/auth");
const bcrypt = require("bcrypt");
const { User, validate, encryptp,comparep } = require("../models/user.model");
const Adress = require('../models/Adress.model')
const Cuidadoradulto = require('../models/cuidador_adulto.model')
const express = require("express");
const router = express.Router();

//ME
router.get("/current", auth, async (req, res) => {
  const user = await User.findById(req.user._id).select("-password");
  //-__v y - ids?
  const addr = await Adress.findById( user.idDireccion);
  res.send(user+addr);
});

//Signup Crear cuenta 
router.post("/", async (req, res) => {
  // validate the request body first
  //const { error } = validate(req.body);
  //if (error) return res.status(400).send(error.details[0].message);

  const { nombre, apellidos, email, password, fechadenacimiento, numerodecelular, saldo, rol, verificado, baneado, calle, numeroexterior,numerointerior,ciudad,codigopostal } = req.body;
  //find an existing user
  let user = await User.findOne({ email: req.body.email });
  if (user) return res.status(400).send("User already registered.");

  //Creating a new Adress
  const adress = new Adress({
    calle,
    numeroexterior,
    numerointerior,
    ciudad,
    codigopostal
  });
  await adress.save();

  // Creating a new User
  user = new User({
    nombre,
    apellidos,
    email,
    password,
    fechadenacimiento,
    numerodecelular,
    saldo,
    rol,
    verificado,
    baneado,
    idDireccion: adress._id.valueOf()
});
  user.password = await encryptp(password);
  await user.save();

  const token = user.generateAuthToken();
  res.header("x-auth-token", token).send({
    _id: user._id,
    nombre: user.nombre,
    email: user.email
  });
});

//Login
router.post("/login", async (req, res) => {
  const user = await User.findOne({email: req.body.email})
  if(!user) {
      return res.status(404).send("The email doesn't exists")
  }
  //console.log("Comparando: " + req.body.password + " con " + user.password);
  const validPassword = await comparep(req.body.password,user.password);
  if (!validPassword) {
      return res.status(401).send({auth: false, token: null});
  }
  const token = user.generateAuthToken();
  res.header("x-auth-token", token).send({
    _id: user._id,
    nombre: user.nombre,
    email: user.email
  });
});

//Logout
router.get('/logout', function(req, res){
  res.header("x-auth-token", null).send({
    "logout":"logout"
  });
});

//Link user to user.
router.post("/link", auth, async (req, res) => {

});

module.exports = router;
