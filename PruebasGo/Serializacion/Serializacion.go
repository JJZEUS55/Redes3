package main

import (
	"fmt"
	_ "os"
	"encoding/json"
)
//Serializar Estructuras
//La serializacion solo funciona en datos publicos, si son privados lanzara un error al tratar de serializarlos

type Persona struct {
	Nombre string  `json:"nombre"`
	Edad int	`json:"edad"`
	Sexo string	`json:"sexo"`
	Altura float32	`json:"altura"`
	Peso float32	`json:"peso"`
}

func (p *Persona) imprimePersona(){
	fmt.Println("Nombre:", p.Nombre)
	fmt.Println("Edad:", p.Edad)
	fmt.Println("Sexo:", p.Sexo)
	fmt.Println("Altura:", p.Altura)
	fmt.Println("Peso:", p.Peso)
}

func main(){

	persona1 := Persona{Nombre:"Pancho Pantera",
				Peso:74.5,
				Altura:1.80,
				Sexo:"Hombre",
				Edad:28}

	//SERIALIZANDO
	bytes, err := json.Marshal(persona1)
	if err != nil{
		fmt.Println("No se puede serializar esto", persona1)
	}
	fmt.Printf(string(bytes))

	//DESERIALIZANDO
	var auxPersona Persona
	err = json.Unmarshal(bytes, &auxPersona)
	if err != nil {
		fmt.Println("No se puede deserializar esto")
	}
	auxPersona.imprimePersona()
}

//Serializar datos simples
func serInt(){
	var x = 5
	bytes, err := json.Marshal(x)
	if err != nil {
		fmt.Println("Can't serislize", x)
	}

	fmt.Printf("%v => %v, '%v'\n", x, bytes, string(bytes))

	// Deserialize int
	var r int
	err = json.Unmarshal(bytes, &r)
	if err != nil {
		fmt.Println("Can't deserislize", bytes)
	}

	fmt.Printf("%v => %v\n", bytes, r)
}




