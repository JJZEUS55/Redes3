import time


def main():
    print("Hola")
    leer_archivos()

def leer_archivos():
    archivo = open("alert.full", "r")
    while 1:
        linea = archivo.readline()
        if not linea:
            time.sleep(1)
            break
        else:
            print(linea)

def busqueda_hora(linea):


main()