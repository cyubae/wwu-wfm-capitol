http://localhost:8080/partner-interface/contractdetails



{
   "processinstance_id_bvis": "1",
   "order":{
      "order_id":3,
      "request_date":"2016-05-20",
      "fleet_rental":true,
      "inquiry_text":"Mr Mustermann is one of our best customers. Can you accept a discount for him?",
      "user":{
         "firstname":"Max",
         "surname":"Mustermann",
         "email":"m.mustermann@beispiel.de",
         "phone_number":"0123-45678",
         "street":"Beispielstr.",
         "house_number":"3a",
         "postcode":"48149",
         "city":"Muenster",
         "country":"Germany",
         "date_of_birth":"1980-01-01",
         "company":true,
         "company_name":"Aldi"
      },
      "car":[
         {
            "registration_number":"MS-MM-200",
            "brand":"Volkswagen",
            "type":"car",
            "model":"Multivan",
            "vehicle_identification_number":"W0L000051T2123456",
            "fuel_type":"diesel",
            "ps":102,
            "construction_year":2002
         },
         {
            "registration_number":"MS-MM-301",
            "brand":"Tesla Motors",
            "type":"car",
            "model":"S",
            "vehicle_identification_number":"T5L5430051T2123456",
            "fuel_type":"diesel",
            "ps":198,
            "construction_year":2014
         },
         {
            "registration_number":"MS-MM-401",
            "brand":"MAN",
            "type":"truck",
            "model":"LE 8.180",
            "vehicle_identification_number":"MM4N30051T2123456",
            "fuel_type":"petrol",
            "ps":300,
            "construction_year":2006
         }
      ],
      "insurance":{
         "insurance_id":32,
         "type":"partial",
         "deductible":200.00,
         "pick_up_date":"2017-01-01",
         "return_date":"2017-12-31",
         "estimated_of_cost":860.00
      }
   }
}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

http://localhost:8080/partner-interface/contractstatus

{
"processinstance_id_bvis": "fc9432hjfxn3423fnvbvg",
"processinstance_id_capitol": "3vc5wsdcvisph344r9uvg",
"order": {
    "order_id": 3,
    "request_date": "2016-05-21",
    "contract_status": 1
    }
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


http://localhost:8080/partner-interface/reminder


{
"processinstance_id_bvis": "fc9432hjfxn3423fnvbvg",
"processinstance_id_capitol": "3vc5wsdcvisph344r9uvg",
"order": {
    "order_id": 3,
    "request_date": "2016-05-21"
    }
}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


http://localhost:8080/partner-interface/claimdetails


{
   "processinstance_id_bvis": "fc9432hjfxn3423fnvbvg",
   "request_date":"2016-05-24",
   "claim":{
      "claim_id":43,
      "insurance_id":32,
      "vehicle_identification_number":"W0L000051T2123456",
      "order_id":3,
      "damage_date":"2016-05-21",
      "damage_address":"Schlossplatz 43 45464 M�nster",
      "claim_description":"Crash into the mansion.",
      "workshop_price":319.99,
      "parties_involved":true,
      "involved_parties":[
         {
            "firstname":"Max",
            "surname":"Mustermann",
            "email":"m.mustermann@beispiel.de",
            "phone_number":"0123-45678",
            "street":"Beispielstr.",
            "house_number":"3a",
            "postcode":"48149",
            "city":"M�nster",
            "country":"Germany",
            "date_of_birth":"1980-01-01",
            "company":"",
            "has_insurance":true,
            "insurance":{
               "company":"Allianz",
               "street":"Auf dem Steinweg",
               "house_number":"1b",
               "postcode":"54678",
               "city":"Hamburg",
               "country":"Germany"
            }
         },
         {
            "firstname":"Max",
            "surname":"Mustermann",
            "email":"m.mustermann@beispiel.de",
            "phone_number":"0123-45678",
            "street":"Beispielstr.",
            "house_number":"3a",
            "postcode":"48149",
            "city":"M�nster",
            "country":"Germany",
            "date_of_birth":"1980-01-01",
            "company":"",
            "has_insurance":true,
            "insurance":{
               "company":"Allianz",
               "street":"Auf dem Steinweg",
               "house_number":"1b",
               "postcode":"54678",
               "city":"Hamburg",
               "country":"Germany"
            }
         }
      ]
   }
}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

http://localhost:8080/partner-interface/feedback


{
    "processinstance_id_bvis": "fc9432hjfxn3423fnvbvg",
	"processinstance_id_capitol": "3vc5wsdcvisph344r9uvg",
    "decision": {
        "claim_id": 534,
        "claim_status": 2,
        "description": "    There is a mistake."
    }
}

