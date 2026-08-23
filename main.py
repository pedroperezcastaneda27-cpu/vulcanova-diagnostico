import os
from kivymd.app import MDApp
from kivymd.uix.screen import MDScreen
from kivymd.uix.button import MDRaisedButton
from kivymd.uix.label import MDLabel
from kivymd.uix.boxlayout import MDBoxLayout
from kivy.utils import platform

try:
    import serial
except ImportError:
    serial = None

class DashboardScreen(MDScreen):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        
        layout = MDBoxLayout(
            orientation='vertical',
            padding=20,
            spacing=15
        )
        
        self.title_label = MDLabel(
            text="Vulcanova Diagnostic Pro",
            halign="center",
            font_style="H4",
            size_hint_y=None,
            height=50
        )
        
        self.status_label = MDLabel(
            text="Estado: Desconectado",
            halign="center",
            theme_text_color="Hint",
            size_hint_y=None,
            height=40
        )
        
        self.connect_btn = MDRaisedButton(
            text="Conectar OBD2",
            pos_hint={"center_x": 0.5},
            on_release=self.connect_obd
        )
        
        layout.add_widget(self.title_label)
        layout.add_widget(self.status_label)
        layout.add_widget(self.connect_btn)
        self.add_widget(layout)

    def connect_obd(self, instance):
        if platform == 'android':
            self.request_android_permissions()
        
        if serial:
            self.status_label.text = "Módulo Serial / Bluetooth Detectado"
        else:
            self.status_label.text = "Iniciando escaneo de interfaz..."

    def request_android_permissions(self):
        try:
            from android.permissions import request_permissions, Permission
            request_permissions([
                Permission.BLUETOOTH,
                Permission.BLUETOOTH_ADMIN,
                Permission.BLUETOOTH_CONNECT,
                Permission.BLUETOOTH_SCAN,
                Permission.ACCESS_FINE_LOCATION
            ])
        except Exception as e:
            self.status_label.text = f"Permisos: {str(e)}"

class VulcanovaApp(MDApp):
    def build(self):
        self.theme_cls.primary_palette = "Blue"
        self.theme_cls.theme_style = "Dark"
        return DashboardScreen()

if __name__ == "__main__":
    VulcanovaApp().run()
      
