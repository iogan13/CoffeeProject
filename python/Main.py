import RFID
import Tkinter as tk
import Queue
import threading

root_window = tk.Tk()
root_window.title("CoffeeProject")
#root_window.attributes("-fullscreen", True)

payment_done = True
payment_result = False
payButtonText = tk.StringVar()
payButtonText.set("Push to pay!")
q = Queue.Queue()
RFIDUnit = RFID.RFID()

def process_or_cancel_payment_async():
    global payment_done
    if payment_done:
        payment_done = False
        t = threading.Thread(target = process_payment)
        t.daemon = True
        t.start()
        payButtonText.set("Cancel!")
        root_window.after(100, check_payment_process)
    else:
        RFIDUnit.stop_processing_payment()
        

def process_payment():
    global payment_done, payment_result
    payment_result = RFIDUnit.start_processing_payment()
    payment_done = True

def check_payment_process():
    global payment_done, payment_result
    if payment_done:
        if payment_result != RFIDUnit.PAYMENT_ERR:
            payButtonText.set("Push to pay!")
        else:
            payButtonText.set("Payment failed!")
    else:
        root_window.after(100, check_payment_process)
            

cupsLabel = tk.Label(root_window, text = "Set count of cups!")
cupsLabel.grid(pady = 10, row = 0, column = 0)
cupsEntry = tk.Entry(root_window)
cupsEntry.grid(pady = 10, row = 1, column = 0)
payButton = tk.Button(root_window, textvariable = payButtonText, command = process_or_cancel_payment_async)
payButton.grid(pady = 10, row = 2, column = 0)

currentStateLabel = tk.Label(root_window, text = "Current state:")
currentStateLabel.grid(row = 0, column = 1)
currentStateValueLabel = tk.Label(root_window)
currentStateValueLabel.grid(row = 1, column = 1)
currentPriceLabel = tk.Label(root_window, text = "Current price:")
currentPriceLabel.grid(row = 2, column = 1)
currentPriceValueLabel = tk.Label(root_window)
currentPriceValueLabel.grid(row = 3, column = 1)

tk.mainloop()
