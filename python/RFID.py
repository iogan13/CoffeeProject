#!/usr/bin/env python
# -*- coding: utf8 -*-

import RPi.GPIO as GPIO
import MFRC522
import signal
import struct
import time

class RFID:

    STATE_WAIT_FOR_CARD = 0
    STATE_WAIT_FOR_NO_CARD = 1
    STATE_PROCESS_CARD = 2

    PAYMENT_DONE = 0
    PAYMENT_ERR = 1
    PAYMENT_CANCELED = 2

    processing_payment_active = True

    MIFAREReader = MFRC522.MFRC522()

    # err_counter = 0
    state = STATE_WAIT_FOR_NO_CARD

    def __init__(self):
        return

    def cleanup(self):
        GPIO.cleanup()

    def stop_processing_payment(self):
        #self.MIFAREReader.MFRC522_StopCrypto1()
        self.processing_payment_active = False


    def start_processing_payment(self):
        self.processing_payment_active = True
        RESULT = self.PAYMENT_ERR
        state = self.STATE_WAIT_FOR_CARD
        while self.processing_payment_active:
            if state == self.STATE_WAIT_FOR_CARD: 
                (status,TagType) = self.MIFAREReader.MFRC522_Request(self.MIFAREReader.PICC_REQIDL)
                if status == self.MIFAREReader.MI_OK:
                    (status,uid) = self.MIFAREReader.MFRC522_Anticoll()
                    if status == self.MIFAREReader.MI_OK:
                        state = self.STATE_PROCESS_CARD
            elif state == self.STATE_PROCESS_CARD:
                key = [0xFF,0xFF,0xFF,0xFF,0xFF,0xFF]
                self.MIFAREReader.MFRC522_SelectTag(uid)

                status = self.MIFAREReader.MFRC522_Auth(self.MIFAREReader.PICC_AUTHENT1A, 8, key, uid)
                # Check if authenticated
                if status == self.MIFAREReader.MI_OK:
                    (status, backData, backLen) = self.MIFAREReader.MFRC522_Read(8)
                    if status == self.MIFAREReader.MI_OK and len(backData) == 16:
                        value = (backData[3] << 24) | (backData[2] << 16) | (backData[1] << 8) | backData[0]
                        value += 100
                        sendData = [value & 0xFF, (value >> 8) & 0xFF, (value >> 16) & 0xFF, (value >> 24) & 0xFF, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00]
                        self.MIFAREReader.MFRC522_Write(8, sendData)
                        self.MIFAREReader.MFRC522_StopCrypto1()
                        RESULT = self.PAYMENT_DONE
                return RESULT
            time.sleep(0.1)
        return self.PAYMENT_CANCELED

            # elif state == STATE_WAIT_FOR_NO_CARD:
                
            #     # Scan for cards    
            #     (status,TagType) = MIFAREReader.MFRC522_Request(MIFAREReader.PICC_REQIDL)
            #     # If no card is found
            #     if status != MIFAREReader.MI_OK:
            #         err_counter += 1
            #     else:
            #         err_counter -= 1
            #     if err_counter > 2:
            #         err_counter = 0
            #         state = STATE_WAIT_FOR_CARD

