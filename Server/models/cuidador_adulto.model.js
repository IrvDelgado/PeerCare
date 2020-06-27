const {Schema, model}= require('mongoose');

const cuidadoradultoSchema = new Schema({
    idCuidador: String,
    idadultomayor: String    
});

module.exports = model('CuidadorAdulto', cuidadoradultoSchema)
