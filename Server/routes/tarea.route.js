const auth = require("../middleware/auth");
const { Tarea } = require("../models/Tarea.model");
const Adress = require('../models/Adress.model')
const express = require("express");
const router = express.Router();

//Crear tarea a una direccion. 
router.post("/add",auth, async (req, res) => {

    const {idSolicitante,titulo, descripcion, costo, checkintime, checkouttime, duracion, idDireccion } = req.body;
    
    const task = new Tarea({
        titulo,
        descripcion,
        costo,
        idDireccion,
        checkintime,
        checkouttime,
        duracion,
        idSolicitante, 
        idProveedor:"",
        status: "publicada"
      });


    await task.save();

    res.send("ok");
});



//solicitar atender tarea
router.post("/solicita",auth, async (req, res) => {
    //parametros: Que tarea y quien

    //Actualiza el proveedor y el estatus a solicitada.
});

/*
//aprueba atender tarea
router.post("/aprueba",auth, async (req, res) => {

});

//rechaza atender tarea
router.post("/rechaza",auth, async (req, res) => {

});

//completa tarea
router.post("/completa",auth, async (req, res) => {

});

//obtener una tarea (agregar parametro id)
router.get("/gettask",auth, async (req, res) => {

});

//obtener todas las tareas (no completadas.?)
router.get("/gettasks",auth, async (req, res) => {

});
*/

module.exports = router;