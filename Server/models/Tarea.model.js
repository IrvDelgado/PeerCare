const {Schema, model}= require('mongoose');

const tareaSchema = new Schema({
    titulo: String,
    descripcion: String,
    costo: Number,
    idDireccion: {
        type: String,
        required: true,
    },
    checkintime: String,
    checkouttime: String,
    duracion: Number,
    idSolicitante: String,
    idProveedor: String,
    status: String
});

module.exports = model('Tarea', tareaSchema)
