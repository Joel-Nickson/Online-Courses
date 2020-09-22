package main

import ("fmt"
  "strconv"
  "sort")

func main() {
  //sl := []int {5,7,3}
  sl := make([]int ,3 ,10)
    //sl[0]=0
    //sl[1]=7
    //sl[2]=3
  //s2 := make([]int , 3)
  var n string
  i:=0
  for i<4{
    fmt.Scan(&n)
    v, err := strconv.ParseInt(n , 10,64)
    if err==nil{
      if i>2{
      sl = append(sl ,int(v))
      } else {
        sl[0]=int(v)
      }
      //fmt.Printf("-> %d",v)
      //sl=s2
      sort.Ints(sl)
      fmt.Println(sl)
    }else if n=="x"||n=="X"{
      break
    }else{
      fmt.Println("****Try Again****")
    } 
    if i<3{i++}
  }
}