# ListaCompra4
App Android de la lista de la compra, usando una clase Application y empleando SQLite para el almacenamiento.


El acceso a SQLite se hace a través, inicialmente, de la clase **SQLiteOpenHelper**, de la que se hereda una clase específica, **SqlIO**, para crear y acceder a nuestra base de datos particular.


Para tener siempre acceso a la base de datos, se emplea el objeto de la clase **Aplication**, que es accesible desde cualquier actividad (**Activity**), a través del método **Activity**.*getApplication()*.
