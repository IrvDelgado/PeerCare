const {Schema, model}= require('mongoose');

const adressSchema = new Schema({
    calle: String,
    numeroexterior: String,
    numerointerior: String,
    ciudad: String,
    codigopostal: String
});

module.exports = model('Adress', adressSchema)
