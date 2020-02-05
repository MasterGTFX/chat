import logging
from chatterbot import ChatBot
import ChatConnector
import json

logging.basicConfig(level=logging.INFO)

chat = ChatConnector.ChatConnector("localhost", 12121)
firstMessage = chat.recv()

print(firstMessage)
botName = firstMessage['botName']
bot = ChatBot(botName)


data = {
    'messageType': 'botMessage',
    'botName': botName,
    'message': 'I have been created'
}

json_data = json.dumps(data)
chat.send(json_data)
while True:
    in_message = chat.recv()
    print(in_message)
    out_message = bot.get_response(in_message['message'])
    data['message'] = str(out_message)
    json_data = json.dumps(data)
    print(json_data)
    chat.send(json_data)
