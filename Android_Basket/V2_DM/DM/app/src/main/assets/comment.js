function sayHello() {
    alert('hola'+ document.getElementById('comment').value + "!!!");
}

function usoDato() {

    var array = new Array();

    //se asigna el valor que se recoge de parametro
    array.push(param);

    //se recorre el array para mostrar lo que se va ingresando
    for( var i=0 in array )
    {
       console.log(array[ i ]);
    }

//    document.write( array + "<br />" );
 }