from multiprocessing import Process, freeze_support
import logging
from chatterbot import ChatBot
from chatterbot.trainers import UbuntuCorpusTrainer
logging.basicConfig(level=logging.INFO)
import ConsoleTest

if __name__ == '__main__':
    chatbot = ChatBot('Example Bot')
    freeze_support()
    trainer = UbuntuCorpusTrainer(chatbot)
    # Start by training our bot with the Ubuntu corpus data
    trainer.train()
