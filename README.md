:arrows_clockwise:
<h1 align="center"> Migrador de miembros de Asepy</h1>

## Requisitos
```
- Java 1.8 o superior
- Maven
```

## Instalación

```
Crear el archivo application.properties con el acceso a la base de datos. Un ejemplo del mismo se encuentra en resources/application.properties.example
```

## Compilación

```
mvn package
```

## Ejecución

El archivo csv de se debe llamar **input.csv**
```
cd target
java -jar migrator-{version}.jar
```

## Autor

👤 **Asepy**

- Github: [@Asepy](https://github.com/Asepy)