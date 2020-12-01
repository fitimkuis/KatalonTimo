# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class KatalonPython(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_katalon_python(self):
        driver = self.driver
        driver.get("https://katalon-demo-cura.herokuapp.com/")
        driver.find_element_by_id("btn-make-appointment").click()
        self.assertEqual("Make Appointment", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Make Appointment'])[1]/following::h2[1]").text)
        driver.find_element_by_id("combo_facility").click()
        Select(driver.find_element_by_id("combo_facility")).select_by_visible_text("Seoul CURA Healthcare Center")
        driver.find_element_by_id("combo_facility").click()
        driver.find_element_by_id("chk_hospotal_readmission").click()
        driver.find_element_by_id("radio_program_medicaid").click()
        driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Visit Date (Required)'])[1]/following::span[1]").click()
        driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sa'])[1]/following::td[36]").click()
        driver.find_element_by_id("txt_comment").click()
        driver.find_element_by_id("txt_comment").clear()
        driver.find_element_by_id("txt_comment").send_keys("Python")
        driver.find_element_by_id("btn-book-appointment").click()
        self.assertEqual("Appointment Confirmation", driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Make Appointment'])[1]/following::h2[1]").text)
        driver.find_element_by_link_text("Go to Homepage").click()
        driver.close()
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
