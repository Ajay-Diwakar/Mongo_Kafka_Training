db.createCollection("users",{
    validator: {
        $jsonSchema:{
            bsonType:"object",
            required:["name","age","email"],
            properties: {
                name:{
                    bsonType: "string",
                    description: "must be string"
                }
                }
        }
    }    
})