# vttp5_day17l-
T1 read carpark data, display as table in own html

T2 read joke, display as a list?

in svc
controller is svc for ui
restcontroller is svc for api

json obj in carparksvc actually dont need, just shows how data name map to our own name

carparkservice calls the api, thn maps to our object

in restcontroller - carpark controller, if api, the return type is responseentity, body is the content which is carparks

parse the date with dateformat first, view date time format java documentation

Use thymeleaf date format to see how it is formatted on html 

date save as epoch?

getForEntity gets header all so must use getbody(), this one need ResponseEntity, and payload use the getbody().
getForObject gets only data body

entity allows you to reject like malicious info, like if .xml come in, but you only accept .JSON

Docker can stop with first 4 digit of the docker id

login details
Nmae is the key, epoch long date is the value


in joke html, no th:object, so data is in body but query parameter style?