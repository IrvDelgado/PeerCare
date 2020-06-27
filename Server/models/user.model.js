const config = require('config');
const jwt = require('jsonwebtoken');
const Joi = require('joi');
const mongoose = require('mongoose');
const bcrypt = require("bcrypt");

//simple schema
const UserSchema = new mongoose.Schema({
  nombre: {
    type: String,
    required: true,
    minlength: 3,
    maxlength: 45
  },
  apellidos: {
    type: String,
    required: false,
    minlength: 3,
    maxlength: 45
  },
  email: {
    type: String,
    required: true,
    minlength: 5,
    maxlength: 255,
    unique: true
  },
  password: {
    type: String,
    required: true,
    minlength: 3,
    maxlength: 255
  },
  fechadenacimiento: {
    type: String,
    required: true,
    minlength: 3,
    maxlength: 45
  },
  numerodecelular: {
    type: String,
    required: true,
    minlength: 7,
    maxlength: 15
  },
  saldo: {
    type: Number,
    required: false
  },
  rol: {
    type: String,
    required: true,
  },
  verificado: {
    type: Boolean,
    required: false
  },
  baneado: {
    type: Boolean,
    required: false
  },
  idDireccion: {
    type: String,
    required: true,
  }
});


//custom method to generate authToken 
//Pendiente checar expiracion
UserSchema.methods.generateAuthToken = function() { 
  const token = jwt.sign({ _id: this._id}, config.get('myprivatekey')); //get the private key from the config file -> environment variable
  return token;
}

const User = mongoose.model('User', UserSchema);

//function to validate user 
function validateUser(user) {
  const schema = {
    nombre: Joi.string().min(3).max(50).required(),
    apellidos: Joi.string().min(3).max(45).required(),
    email: Joi.string().min(5).max(255).required().email(),
    password: Joi.string().min(3).max(255).required(),
    fechadenacimiento: Joi.string().min(3).max(45).required(),
    numerodecelular: Joi.string().min(7).max(15).required(),
    saldo: Joi.number(),
    rol: Joi.string().required(),
    verificado: Joi.boolean(),
    baneado: Joi.boolean(),
    idDireccion: Joi.string()
  };

  return Joi.validate(user, schema);
}

async function encryptPahessword(password) {
  const salt = await bcrypt.genSalt(10);
  return bcrypt.hash(password, salt);
};

async function comparePassword  (password, hashedp) {
  //console.log("Comparando: " + password + " con " + hashedp);
  return bcrypt.compare(password, hashedp);
};

exports.User = User; 
exports.validate = validateUser;
exports.encryptp = encryptPahessword;
exports.comparep = comparePassword;