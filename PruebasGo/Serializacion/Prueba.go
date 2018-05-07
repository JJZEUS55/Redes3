package main

import (
"encoding/json"
"fmt"
)

type Symptoms struct {
	Id   int8    `json:"id"`
	Name string  `json:"name"`
	Info *string `json:"info"`
	// Info string `json:"info,omitempty"`
}

type Item struct {
	Name        string     `json:"name"`
	Symptoms    []Symptoms `json:"symptoms"`
	Type        string     `json:"type"`
	Img         string     `json:"img"`
	Description string     `json:"description"`
	New         bool       `json:"new"`
	Links       []string   `json:"links"`
}

func (i* Item)imprimeItem()  {
	fmt.Println("Nombre:",i.Name)
	fmt.Println("Symtoms:",i.Symptoms)
	fmt.Println("Type:",i.Type)
}


func main() {
	s := Symptoms{Id: 1, Name: "Dry and Soar Throat", Info: nil}
	m := Item{Name: "Strepsils", Symptoms: []Symptoms{s},
		Type:        "Sweets",
		Img:         "Strepsils",
		Description: "Cure for soar and dry throat", New: false, Links: []string{}}

	bs, err:= json.Marshal(m)
	if err != nil{
		fmt.Println("No se puede serializar esto")
	}
	fmt.Println(string(bs))

	var auxM Item
	err = json.Unmarshal(bs, &auxM)
	if err != nil{
		fmt.Println("No se puede deserializar esto")
	}
	auxM.imprimeItem()
}
