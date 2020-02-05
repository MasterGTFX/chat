from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer


class CreateBot:

    def __init__(self, name):
        self.name = name
        self.bot = self.create_bot()
        self.train_bot()

    def create_bot(self):
        bot = ChatBot(
            self.name,
            storage_adapter='chatterbot.storage.SQLStorageAdapter',
            logic_adapters=[
                {
                    'import_path': 'chatterbot.logic.MathematicalEvaluation'
                },
                {
                    'import_path': 'chatterbot.logic.BestMatch',
                    'default_response': 'I am sorry, but I do not understand.',
                    'maximum_similarity_threshold': 0.50
                },
            ],
            database_uri='sqlite:///database.sqlite3',
            preprocessors=[
                'chatterbot.preprocessors.clean_whitespace',
                'chatterbot.preprocessors.unescape_html',
                'chatterbot.preprocessors.convert_to_ascii'

            ]
        )
        return bot

    def train_bot(self):
        trainer = ChatterBotCorpusTrainer(self.bot)
        trainer.train(
            "chatterbot.corpus.english"
        )

    def get_bot(self):
        return self.bot
