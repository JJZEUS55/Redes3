# -*- coding: utf-8 -*-

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

import wx
import wx.xrc


###########################################################################
## Class frameLectorLogs
###########################################################################

class VistaMenu(wx.Frame):

    def __init__(self, parent):
        wx.Frame.__init__(self, parent, id=wx.ID_ANY, title=wx.EmptyString, pos=wx.DefaultPosition,
                          size=wx.Size(597, 417), style=wx.DEFAULT_FRAME_STYLE | wx.TAB_TRAVERSAL)

        self.SetSizeHintsSz(wx.DefaultSize, wx.DefaultSize)

        bSizer2 = wx.BoxSizer(wx.VERTICAL)

        self.m_staticText2 = wx.StaticText(self, wx.ID_ANY, u"Bienvenido\nAnalizador de Bitacoras", wx.DefaultPosition,
                                           wx.DefaultSize, wx.ALIGN_CENTRE)
        self.m_staticText2.Wrap(-1)
        self.m_staticText2.SetFont(wx.Font(wx.NORMAL_FONT.GetPointSize(), 75, 90, 92, False, wx.EmptyString))

        bSizer2.Add(self.m_staticText2, 0, wx.ALIGN_CENTER_HORIZONTAL | wx.ALL, 5)

        self.btnOssec = wx.Button(self, wx.ID_ANY, u"Bitacoras OSSEC", wx.DefaultPosition, wx.Size(200, 60), 0)
        bSizer2.Add(self.btnOssec, 0, wx.ALIGN_CENTER_HORIZONTAL | wx.ALL, 60)

        self.btnOpenVS = wx.Button(self, wx.ID_ANY, u"Bitacoras OpenVS", wx.DefaultPosition, wx.Size(200, 60), 0)
        bSizer2.Add(self.btnOpenVS, 0, wx.ALL | wx.ALIGN_CENTER_HORIZONTAL, 5)

        self.SetSizer(bSizer2)
        self.Layout()

        self.Centre(wx.BOTH)

    def __del__(self):
        pass

app = wx.App(False)
frame = VistaMenu(None)
frame.Show(True)
app.MainLoop()





