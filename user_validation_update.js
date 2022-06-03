db.runCommand({
    collMod: "users",
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
    },
    validationAction: 'warn'    
})