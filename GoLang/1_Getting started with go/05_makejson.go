package main

import ("fmt"
  "encoding/json")

func main() {
  
  /*type struct Person{
    name string
    address string
  }*/
  var name,address string
  var Person = make(map[string]string)
  fmt.Print("name: ")
  fmt.Scanln(&name)
  Person["name"]= name
  fmt.Print("address: ")
  fmt.Scanln(&address)
  Person["address"]= address
  
  //fmt.Print("\n",len(Person))
  barr,_ := json.Marshal(Person)
  fmt.Print("ByteArray: \n",barr)
  
  //_ = json.Unmarshal(barr, &Person)
  /*for k,v := range Person{
    fmt.Print("\n",k,":",v)
  }*/
  //fmt.Print("\n",Person)

}