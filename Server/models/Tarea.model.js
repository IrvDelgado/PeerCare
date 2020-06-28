const mongoose = require('mongoose');

//simple schema
const tareaSchema = new mongoose.Schema({
    titulo: String,
    descripcion: String,
    costo: Number,
    idDireccion:String,
    checkintime: String,
    checkouttime: String,
    duracion: Number,
    idSolicitante: String,
    idProveedor: String,
    status: String
});

const Tarea = mongoose.model('Tarea', tareaSchema);

exports.Tarea = Tarea; 

