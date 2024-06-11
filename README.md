The user is required to log in by entering an Account number and the PIN for that account.

If the Account is found, the user can perform the following actions:

- View my balance: The balance of the account is displayed.
- Deposit: The user is asked to enter an amount, and that amount is added to the balance of the account.
- Withdrawal: The user is asked to select an amount from a list, and the money is withdrawn if the balance supports it.
- Logout: The user is logged out, and the next user is required to log in.
- Exit: The application is stopped.
- If the Account is not found, the user is notified and asked to try again.

Use Cases:
ATM Interaction:

- An ATM uses a Screen to display messages and a Keypad for interaction.
- An ATM uses a BankDatabase that contains the Accounts.
- An Account has an account number and is protected by a PIN.
- An account has a balance on which credit or debit transactions can be applied.
- An ATM supports three types of Transactions: Balance Inquiry, Cash Deposit, and Cash Withdrawal.
- An ATM allows a user to perform a Transaction only if the user is authenticated with their account number and PIN.

Transaction Types:

- Balance Inquiry: Displays the balance of the authenticated user's account on the screen.
- Withdrawal: Allows the user to withdraw $20, $40, $60, $100, or $200 from the authenticated account if the Cash Dispenser has enough bills and the balance supports it.
- Deposit: Allows the user to credit the authenticated account with an entered amount, thus increasing its balance.

User Interaction:

- The Keypad provides user input.
- The Screen displays messages, including formatted amounts.

Extra Features:

- An ATM uses a Cash Dispenser to withdraw money.
- The Cash Dispenser has a limited amount of $20 bills and can only dispense these kinds of bills.
- The number of bills in the Cash Dispenser is not increased when a Deposit Transaction is made.
- During a Withdrawal, the user selects an amount from a list. If the Cash Dispenser has enough bills and the balance supports it, the money is withdrawn.

External File Loading:

- Load the BankDatabase and Cash Dispenser values from an external file to initialize the application.
