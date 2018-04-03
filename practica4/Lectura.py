import time
from tkinter import *
from tkinter import ttk
import os

root = Tk()
topFrame = Frame(root)
topFrame.pack()

botFrame = Frame(root)
botFrame.pack(side=BOTTOM)

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

def limpia():
    os.system("cls" if os.name=="nt" else "clear")


def busquedanombre():
    limpia()
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
                contador +=1


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
            if "jj1" in line and "terminated" in line:
                contador += 1
                print(line)
            else:
                contador += 1


btnBuscar = Button(topFrame, text="Buscar", command=usuariosconectados)
btnBuscar.pack(side=LEFT)

root.mainloop()
