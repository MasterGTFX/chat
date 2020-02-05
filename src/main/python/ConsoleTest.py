from autocorrect import spell


class consoleTest():
    bot = None

    def __init__(self, bot):
        self.bot = bot
        print('Type something to begin...')
        while True:
            try:
                user_input = spell(input())
                bot_response = bot.get_response(user_input)
                print(bot_response)
            except (KeyboardInterrupt, EOFError, SystemExit):
                break
