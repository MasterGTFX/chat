from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer

import CreateBot
import ConsoleTest

chatbot = ChatBot(
    "My ChatterBot",
    storage_adapter="chatterbot.storage.SQLStorageAdapter",
    database_uri='mysql://dominikb:S0wcAfMh35v88hFt@mysql.agh.edu.pl:3306/dominikb'
)
trainer = ChatterBotCorpusTrainer(chatbot)
trainer.train(
    "chatterbot.corpus.english"
)
ConsoleTest.consoleTest(chatbot)
7