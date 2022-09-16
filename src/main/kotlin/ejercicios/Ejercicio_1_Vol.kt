package ejercicios


import java.io.File
import java.text.SimpleDateFormat
import java.util.Scanner

fun main(args: Array<String>) {
    var f = File.listRoots()[0]
    val sc = Scanner(System.`in`)

    if (f.isDirectory())
        llistaDirectoriExtendido(f)
    println("\nIntroduce el numero del directorio: ")
    var ent = sc.nextInt()

    while (ent != -1) {

        if (ent < -1 || ent > f.listFiles().size)
            println("Introduce un numero valido")
        else {
            if (ent == 0) {
                if (f == File.listRoots()[0])
                    println("Ya estas en el directorio raiz. No hay directorio padre.")
                else {
                    f = f.parentFile;
                    llistaDirectoriExtendido(f);
                }
            } else {
                if (f.listFiles().sorted()[ent - 1].exists() && f.listFiles().sorted()[ent - 1].canRead()) {
                    if (f.listFiles().sorted()[ent - 1].isDirectory) {
                        f = f.listFiles().sorted()[ent - 1]
                        llistaDirectoriExtendido(f);
                    } else
                        println("No se puede imprimir el archivo. Intenta con un directorio.")
                } else
                    println("No existe el directorio o no se puede leer.")
            }
        }

        println("\nIntroduce el numero del directorio: ")
        ent = sc.nextInt()
    }
}

fun llistaDirectoriExtendido(f: File) {
    val s = "Llista de fitxers i directoris del directori " + f.getCanonicalPath()
    println(s)
    println("-".repeat(s.length))
    println("" + 0 + ".- " + "Directorio padre")
    var ind = 1
    val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
    for (e in f.listFiles().sorted()) {

        print("" + ind + ".- ")

        if (e.isFile())
            print("-")
        else if (e.isDirectory())
            print("d")

        if(e.canRead())
            print("r")
        else
            print("-")

        if(e.canWrite())
            print("w")
        else
            print("-")

        if(e.canExecute())
            print("x")
        else
            print("-")

        print("\t" + e.length() + "\t" + sdf.format(e.lastModified()) + "\t" + e.name)

        ind++
        print("\n")
    }
}