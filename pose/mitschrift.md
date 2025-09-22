# Mitschrift

## 17.09.2025

### nullability
? 
?.
?? -> wenn links null is ist rechts auch null
??= ->

### record
* geht schneller
* alles gleich immutable
* man muss keine Kopie machen wenn Daten geändert werden

struct
* record struct ist nicht immutable record schon
* 16 byte Obergrenze
```
var sarah = new Student(12, "Sarah", "Ngimbi", 51.2D)

//sarah.LastName = "Kis" -> geht nicht weil alle Properties read only sind
var sarah2 = sarah with( 
    LastName="Kis"
)

public sealed record Student(int Id, string FirstName, string LastName, double Weight) // für alle Variablen im Constructor werden Properties erstellt
```

```
public readonly record strucct Student(int Id, string FirstName, string LastName, double Weight) 
```


### Allgemein
int = 4 byte
string = 8 byte