import socket
import json


class ChatConnector:
    socket = None

    def __init__(self, host, port):
        self.connect(host, port)

    def connect(self, host, port):
        self.socket = socket.socket()
        self.socket.connect((host, port))
        print("connected")
        return self

    def send(self, data):
        if not self.socket:
            raise Exception('You have to connect first before sending data')
        print("sending message")
        data = data.encode('utf8')
        self.socket.sendall(data+b'\n')
        return self

    def recv(self):
        if not self.socket:
            raise Exception('You have to connect first before receiving data')
        print("waiting for response")
        data = self.socket.recv(4096).decode('utf8')
        json_data = json.loads(data)
        return json_data


# with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
#   s.connect((HOST, PORT))
#    print("Connected to server")
# print("Connected")
# data = s.recv(1024)
# print("Received")
# s.sendall(b'Hello, world')
# print("Sent")

# print('Received', repr(data))
