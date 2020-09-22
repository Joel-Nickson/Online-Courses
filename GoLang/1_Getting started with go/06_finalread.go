package main

import ("fmt"
  "io/ioutil"
  "strings")

func main() {
  var name string
  fmt.Print("Name of file: ")
  fmt.Scanln(&name)
  
  type Person struct{
    fname string
    lname string
  }
  arr := []Person{}
  //p1:=new(Person)
  dat,_ :=ioutil.ReadFile(name) // .txt file
     /* 
     Joel Nickson
     Nikhil Nickson
     Liji John
     Nickson Sebastian
     */

  //fmt.Println(string(dat))  //dat===byte array of file 
  slice := strings.Split(string(dat),"\n")

  for i:=0 ;i<len(slice) ;i++ {
    s := strings.Split(slice[i]," ")
    p :=Person{fname:string(s[0]),lname:string(s[1])}
    arr=append(arr,p)
    fmt.Println(arr[i].fname+" "+arr[i].lname)
  }
}

//Print samee as file