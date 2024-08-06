import random
import copy
import time

class Board:
    def __init__(self, board, score):
        self.board = copy.deepcopy(board)
        self.score = score

class AI:
    @staticmethod
    def make_ai_move(board, turn, discs_needed_to_win):
        best_ai_move = AI.calculate_move(board, turn, discs_needed_to_win)
        best_player_move = AI.calculate_move(board, 'O' if turn == 'X' else 'X', discs_needed_to_win)

        does_enemy_win = False
        will_player_win = Connect4.check_if_win(best_player_move, discs_needed_to_win)
        if (turn == 'X' and will_player_win == 'O') or (turn == 'O' and will_player_win == 'X'):
            does_enemy_win = True

        if does_enemy_win:
            for i in range(len(board)):
                for j in range(len(board[0])):
                    if board[i][j] != best_player_move[i][j]:
                        best_ai_move = copy.deepcopy(board)
                        best_ai_move[i][j] = turn

        return best_ai_move

    @staticmethod
    def calculate_move(board, turn, discs_needed_to_win):
        new_board = copy.deepcopy(board)
        better_move_found = False

        boards = []
        for column_to_check in range(len(new_board[0])):
            placed_board = AI.place_disc(new_board, turn, column_to_check)
            boards.append(Board(placed_board, AI.calculate_cookies(placed_board, turn, discs_needed_to_win)))

        indices_of_highest_scores = []
        highest_score = AI.calculate_cookies(board, turn, discs_needed_to_win)

        for i, board_obj in enumerate(boards):
            if board_obj.score > highest_score:
                indices_of_highest_scores = [i]
                highest_score = board_obj.score
                better_move_found = True
            elif board_obj.score == highest_score:
                indices_of_highest_scores.append(i)

        if better_move_found:
            return boards[random.choice(indices_of_highest_scores)].board
        else:
            return AI.place_disc(board, turn, random.randint(0, len(board[0]) - 1))

    @staticmethod
    def place_disc(board, turn, place_disc_in_column):
        new_board = copy.deepcopy(board)

        if place_disc_in_column > len(new_board[0]) - 1 or place_disc_in_column < 0:
            raise IndexError()

        for row in range(len(new_board) - 1, -1, -1):  # Start from the bottom
            if new_board[row][place_disc_in_column] == ' ':
                new_board[row][place_disc_in_column] = turn
                return new_board

        # If we get here, the column is full
        return Connect4.empty_board(new_board)

    @staticmethod
    def calculate_cookies(test_board, turn, discs_needed_to_win):
        ice_cream = 0
        player_to_check = turn

        for row in range(len(test_board)):
            for column in range(len(test_board[row])):
                # Check horizontals, verticals, and diagonals for wins
                for direction in [(0, 1), (1, 0), (1, 1), (1, -1)]:
                    try:
                        discs_in_a_row = sum(1 for j in range(discs_needed_to_win) if test_board[row + j * direction[0]][column + j * direction[1]] == player_to_check)
                        if discs_in_a_row == discs_needed_to_win:
                            ice_cream += 999
                    except IndexError:
                        pass

                # Give +1 ice_cream for each adjacent chip
                if test_board[row][column] == player_to_check:
                    for dr, dc in [(1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1), (0, -1), (1, -1)]:
                        try:
                            if test_board[row + dr][column + dc] == player_to_check:
                                ice_cream += 1
                        except IndexError:
                            pass

        return ice_cream

class Connect4:
    @staticmethod
    def main():
        game_mode = 0
        num_rows = 6
        num_columns = 7
        discs_needed_to_win = 4

        while True:
            menu_choice = Connect4.get_menu_choice(discs_needed_to_win)

            if menu_choice == 1:
                Connect4.start_game(num_rows, num_columns, game_mode, discs_needed_to_win)
            elif menu_choice == 3:
                return
            elif menu_choice == 4:
                game_mode = Connect4.change_game_mode()
            elif menu_choice == 5:
                num_columns = Connect4.change_board_width(num_columns)
            elif menu_choice == 6:
                num_rows = Connect4.change_board_height(num_rows)
            elif menu_choice == 7:
                discs_needed_to_win = Connect4.change_discs_needed_to_win(num_rows, num_columns, discs_needed_to_win)

    @staticmethod
    def change_discs_needed_to_win(num_rows, num_columns, discs_needed_to_win):
        while True:
            try:
                print(f"\n|| Change Number of Discs-In-a-Row Needed to Win ||\nCurrent: {discs_needed_to_win} Discs.\nEnter a new value: ")
                temp_discs_needed_to_win = int(input())

                if temp_discs_needed_to_win > num_rows:
                    print(f"Your board is only {num_rows} rows tall. Please enter a smaller number of discs.")
                elif temp_discs_needed_to_win > num_columns:
                    print(f"Your board is only {num_columns} columns wide. Please enter a smaller number of discs.")
                elif temp_discs_needed_to_win < 1:
                    print("Invalid Input.")
                else:
                    discs_needed_to_win = temp_discs_needed_to_win
                    break
            except ValueError:
                print("Invalid Input.")

        return discs_needed_to_win

    @staticmethod
    def get_menu_choice(discs_needed_to_win):
        while True:
            try:
                print(f"\n|| Connect {discs_needed_to_win} ||")
                print("1: Start Game\n2: Change Settings\n3: Exit")
                print("Enter your choice: ")
                menu_choice = int(input())

                if menu_choice == 2:
                    while True:
                        try:
                            print("\n|| Settings ||")
                            print("1: Change Game Mode\n2: Change Board Width\n3: Change Board Height\n4: Change number of discs-in-a-row needed to win\n5: Go back")
                            print("Enter your choice: ")
                            menu_choice = int(input()) + 3

                            if 4 <= menu_choice <= 8:
                                return menu_choice
                            elif menu_choice == 8:
                                break
                            else:
                                print("Invalid Input.")
                        except ValueError:
                            print("Invalid Input.")
                elif 1 <= menu_choice <= 3:
                    return menu_choice
                else:
                    print("Invalid Input.")
            except ValueError:
                print("Invalid Input")

    @staticmethod
    def change_game_mode():
        while True:
            try:
                print("\n|| Choose a Game Mode ||\n1: Player vs. Player\n2: Player vs. Computer\nEnter your choice:")
                game_mode = int(input())

                if game_mode in [1, 2]:
                    print("Gamemode set to Player vs. Player." if game_mode == 1 else "Gamemode set to Player vs. Computer.")
                    return game_mode
                else:
                    print("Invalid Input.")
            except ValueError:
                print("Invalid Input.")

    @staticmethod
    def change_board_width(num_columns):
        while True:
            try:
                print(f"\n|| Choose Board Width ||\nCurrent Board Width: {num_columns} Columns\nEnter a New Board Width: ")
                temp_num_columns = int(input())

                if temp_num_columns > 9:
                    print("The maximum board size is 9x9")
                elif temp_num_columns < 3:
                    print("The minimum board size is 3x3")
                else:
                    num_columns = temp_num_columns
                    print(f"New Board Width: {num_columns} Columns")
                    break
            except ValueError:
                print("Invalid Input.")
        return num_columns

    @staticmethod
    def change_board_height(num_rows):
        while True:
            try:
                print(f"\n|| Choose Board Height ||\nCurrent Board Height: {num_rows} Rows\nEnter a New Board Height: ")
                temp_num_rows = int(input())

                if temp_num_rows > 9:
                    print("The maximum board size is 9x9")
                elif temp_num_rows < 3:
                    print("The minimum board size is 3x3")
                else:
                    num_rows = temp_num_rows
                    print(f"New Board Height: {num_rows} Rows")
                    break
            except ValueError:
                print("Invalid Input.")
        return num_rows

    @staticmethod
    def start_game(num_rows, num_columns, game_mode, discs_needed_to_win):
        cpu_character = ""
        turn = "X"
        winner = "none"
        board = [[" " for _ in range(num_columns)] for _ in range(num_rows)]

        if game_mode == 0:
            game_mode = Connect4.change_game_mode()

        if game_mode == 2:
            cpu_character = Connect4.select_player()

        seconds_timer = time.time()

        while winner not in ["X", "O"]:
            Connect4.print_board(board, discs_needed_to_win)

            if turn == "X":
                if cpu_character == "X":
                    board = AI.make_ai_move(board, turn, discs_needed_to_win)
                    print("CPU is Calculating Move...")
                    time.sleep(2)
                else:
                    board = Connect4.place_disc(board, turn)
                turn = "O"
            else:
                if cpu_character == "O":
                    board = AI.make_ai_move(board, turn, discs_needed_to_win)
                    print("CPU is Calculating Move...")
                    time.sleep(2)
                else:
                    board = Connect4.place_disc(board, turn)
                turn = "X"

            winner = Connect4.check_if_win(board, discs_needed_to_win)

        Connect4.print_board(board, discs_needed_to_win)
        print(f"{winner} Wins!")

        seconds_timer = time.time() - seconds_timer
        minutes_timer = int(seconds_timer // 60)
        seconds_timer = int(seconds_timer % 60)

        time.sleep(2)

        if seconds_timer < 60:
            print(f"Game length: {seconds_timer} seconds")
        elif seconds_timer < 120:
            print(f"Game length: {minutes_timer} minute and {seconds_timer} seconds.")
        else:
            print(f"Game length: {minutes_timer} minutes and {seconds_timer} seconds.")
        time.sleep(2)

    @staticmethod
    def print_board(board, discs_needed_to_win):
        total_width_of_board = len(board[0]) * 4
        print("|" + "-" * ((total_width_of_board - 9) // 2) + f"Connect {discs_needed_to_win}" + "-" * ((total_width_of_board - 9) // 2) + "|")

        for row in board:  # Remove 'reversed' here
            print("| " + " | ".join(row) + " |")

        print("  " + "   ".join(str(i + 1) for i in range(len(board[0]))) + "\n")

    @staticmethod
    def empty_board(board):
        return [[" " for _ in range(len(board[0]))] for _ in range(len(board))]

    @staticmethod
    def select_player():
        while True:
            player_character = input("Do you want to play as X or O? (X/O)").upper()
            if player_character == "X":
                print("Player is X and CPU is O.")
                return "O"
            elif player_character == "O":
                print("Player is O and CPU is X.")
                return "X"
            else:
                print("Invalid Input.")

    @staticmethod
    def place_disc(board, turn):
        while True:
            try:
                place_disc_in_column = int(input(f"{turn}: Choose column to place disc in: ")) - 1
                if 0 <= place_disc_in_column < len(board[0]):
                    for row in range(len(board)):
                        if board[row][place_disc_in_column] != " ":
                            if row == 0:
                                print("Invalid Input.")
                                break
                            board[row - 1][place_disc_in_column] = turn
                            return board
                    board[len(board) - 1][place_disc_in_column] = turn
                    return board
                else:
                    print("Invalid Input.")
            except ValueError:
                print("Invalid Input.")

    @staticmethod
    def check_if_win(board, discs_needed_to_win):
        for player in ["X", "O"]:
            for row in range(len(board)):
                for col in range(len(board[0])):
                    for dr, dc in [(0, 1), (1, 0), (1, 1), (1, -1)]:
                        try:
                            if all(board[row + i * dr][col + i * dc] == player for i in range(discs_needed_to_win)):
                                return player
                        except IndexError:
                            continue
        return ""

if __name__ == "__main__":
    Connect4.main()