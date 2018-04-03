import time
from tkinter import *
from tkinter import ttk
import os
import VistaTabla

palabraConexion = ["OK LOGIN",
                   "OK DOWNLOAD",
                   "FAIL DOWNLOAD"]

root = Tk()
topFrame = Frame(root)
topFrame.pack()

botFrame = Frame(root)
botFrame.pack(side=BOTTOM)

# TABLA de VISTA TABLA
tablaC = VistaTabla.App(botFrame)

# HORAS
horaLbl = ttk.Label(topFrame, text="Hora")
horaLbl.pack(side=LEFT)

horaTxt = ttk.Entry(topFrame)
horaTxt.pack(side=LEFT)

# MINUTOS
minLbl = ttk.Label(topFrame, text="Minutos")
minLbl.pack(side=LEFT)

minTxt = ttk.Entry(topFrame)
minTxt.pack(side=LEFT)

# NOMBRE
usuariosLbl = ttk.Label(topFrame, text="Usuario")
usuariosLbl.pack(side=LEFT)

usuarios = ttk.Combobox(topFrame, state="readonly")
usuarios.pack(side=LEFT)
usuarios["values"] = ["sammy",
                      "user1",
                      "jj1",
                      "user2"]

# DIAS
diaLbl = ttk.Label(topFrame, text="Dia")
diaLbl.pack(side=LEFT)

dias = ttk.Combobox(topFrame, state="readonly")
dias.pack(side=LEFT)
dias["values"] = ["Mon",
                  "Tue",
                  "Wed",
                  "Thu",
                  "Fri",
                  "Sat",
                  "Sun"]


# TABLA
# rows = []
# for i in range(5):
#     cols = []
#     for j in range(6):
#         e = Entry(botFrame)
#         e.grid(row=i, column=j)
#         e.insert(END, "%d.%d" % (i, j))
#         cols.append(e)
#     rows.append(cols)
# e.pack()

def busquedanombre():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    nombre = usuarios.get()
    numLineas = sum(1 for line in open("vsftpd1.log"))
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if nombre in line:
                contador += 1
                print(line)
            else:
                contador += 1


def busquedahora():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    numLineas = sum(1 for line in open("vsftpd1.log"))
    hora = horaTxt.get()
    minutos = minTxt.get()
    horaCompleta = hora + ":" + minutos
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if horaCompleta in line:
                contador += 1
                print(line)
            else:
                contador += 1


def busquedadia():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    numLineas = sum(1 for line in open("vsftpd1.log"))
    dia = dias.get()
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if dia in line:
                contador += 1
                print(line)
            else:
                contador += 1


def usuariosconectados():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if valores[0] in line and "terminated" in line:
                contador += 1
                print(line)
            else:
                contador += 1


def recursos_accedidos():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if valores[3] in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    # print(palabra)
                    if "bytes" in line or "Kbyte" in line:
                        print(palabra[9])
                        print(palabra[10])
                        print(palabra[13])
                        print(palabra[14])
                    # formato_dividido(palabra)
            else:
                contador += 1


# DIVIDE CON SPLIT TODA LA CADENA Y LA GUARDA EN UN ARREGLO
def dividir_frases():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if valores[0] in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    #print(palabra)
                    formato_dividido(palabra)
            else:
                contador += 1


# SOLO FORMATO DE IMPRESION y AGREGA VALORES A LA TABLA
def formato_dividido(palabra):
    diaSemana = palabra[0]
    mes = palabra[1]
    dia = palabra[3]
    horaCompleta = palabra[4]
    anio = palabra[5]
    usuario = palabra[8]
    tablaC.llenarTabla(diaSemana, mes, dia, horaCompleta, anio, usuario)

    print("Dia Semana: " + diaSemana)
    print("Mes: " + mes)
    print("Dia: " + dia)
    print("Hora: " + horaCompleta)
    print("Año: " + anio)
    print("Usuario: " + usuario)


btnBuscar = Button(topFrame, text="Buscar", command=recursos_accedidos)
btnBuscar.pack(side=LEFT)

root.mainloop()
