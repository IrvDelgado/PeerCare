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
    const {idtarea,idProveedor} = req.body;

    const tarea = await Tarea.findByIdAndUpdate(
      {_id:idtarea},
      { status: "Solicitada",
      idProveedor
       },
      function(err, result) {
        if (err) {
          res.send(err);
        } else {
          res.send("ok");
        }
      }
      );

    //res.send("ok");
    //Actualiza el proveedor y el estatus a solicitada.
});


//aprueba atender tarea
router.post("/aprueba",auth, async (req, res) => {
  //parametros: Que tarea y quien
  const {idtarea} = req.body;

  const tarea = await Tarea.findByIdAndUpdate(
    {_id:idtarea},
    { status: "En progreso"},
    function(err, result) {
      if (err) {
        res.send(err);
      } else {
        res.send("ok");
      }
    }
    );
});


//rechaza atender tarea
router.post("/rechaza",auth, async (req, res) => {

  //parametros: Que tarea y quien
  const {idtarea} = req.body;

  const tarea = await Tarea.findByIdAndUpdate(
    {_id:idtarea},
    { status: "publicada",
    idProveedor:""
     },
    function(err, result) {
      if (err) {
        res.send(err);
      } else {
        res.send("ok");
      }
    }
    );

});

//completa tarea
router.post("/completa",auth, async (req, res) => {
//parametros: Que tarea y quien
  const {idtarea} = req.body;

  const tarea = await Tarea.findByIdAndUpdate(
    {_id:idtarea},
    { status: "Completada"},
    function(err, result) {
      if (err) {
        res.send(err);
      } else {
        res.send("ok");
      }
    }
    );
});



//obtener una tarea (agregar parametro id)
router.get("/gettask",auth, async (req, res) => {
  const {idtarea} = req.body;
  const tarea = await Tarea.findById(idtarea);
  res.send(tarea);
  
});


//obtener todas las tareas
router.get("/gettasks",auth, async (req, res) => {
  const tareas = await Tarea.find();
  res.send(tareas);
});

//obtener todas las tareas disponibles.
router.get("/getavailabletasks",auth, async (req, res) => {

  const tareas = await Tarea.find({ status: 'publicada'});;
  res.send(tareas);
});


//As an elderly: 
//obtener todas las tareas que tengo publicadas.

//obtener todas las tareas que tengo pendientes de aprobar o rechazar.


//obtener todas las tareas que tengo 

//As a caregiver
//Obtener todas las tareas que he solicitado

//Obtener todas las tareas que tengo confirmadas en progreso. 


module.exports = router;