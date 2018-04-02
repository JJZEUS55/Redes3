import time
from tkinter import *
from tkinter import ttk

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


def busquedanombre():
    openFile = open("vsftpd1.log", "r")
    nombre = usuarios.get()
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            openFile.seek(where)
        else:
            if nombre in line:
                print(line)


def busquedahora():
    openFile = open("vsftpd1.log", "r")
    hora = horaTxt.get()
    minutos = minTxt.get()
    horaCompleta = hora + ":" + minutos
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            openFile.seek(where)
        else:
            if horaCompleta in line:
                print(line)


def busquedadia():
    openFile = open("vsftpd1.log", "r")
    hora = horaTxt.get()
    minutos = minTxt.get()
    horaCompleta = hora + ":" + minutos
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            openFile.seek(where)
        else:
            if horaCompleta in line:
                print(line)


def usuariosconectados():
    openFile = open("vsftpd1.log", "r")
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            openFile.seek(where)
        else:
            if valores[1] and "terminated" in line:
                print(line)


btnBuscar = Button(topFrame, text="Buscar", command=usuariosconectados)
btnBuscar.pack(side=LEFT)

root.mainloop()
