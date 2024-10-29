# Styled Registration Form

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Requirements](#requirements)
- [Setup and Running](#setup-and-running)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [Customization](#customization)
- [Author](#author)

## Description
This project is a JavaFX application that implements a styled registration form with real-time input validation. It's designed to provide a user-friendly interface for collecting user information while ensuring data integrity through immediate feedback on input validity.

## Features
- Stylish and intuitive user interface
- Real-time input validation for all fields
- Custom validation for:
    - First and Last Name (2-25 characters)
    - Email (must be a valid Farmingdale email address)
    - Date of Birth (MM/DD/YYYY format)
    - Zip Code (5-digit number)
- Visual feedback on input validity (green for valid, red for invalid)
- Disabled submit button until all inputs are valid
- Success screen upon successful form submission

## Requirements
- Java Development Kit (JDK) 11 or higher
- JavaFX SDK 11 or higher

## Setup and Running
1. Ensure you have JDK and JavaFX SDK installed on your system.
2. Clone this repository to your local machine.
3. Open the project in your preferred Java IDE.
4. Make sure to add the JavaFX library to your project's classpath.
5. Run the `StyledRegistrationForm` class.

## Project Structure
- `StyledRegistrationForm.java`: The main class containing all the UI and logic implementation.
- `resources/user.png`: The logo image used in the form (ensure this file is present in your resources folder).

## How It Works
1. The application creates a form with fields for First Name, Last Name, Email, Date of Birth, and Zip Code.
2. As the user types, each field is validated in real-time.
3. Invalid inputs are highlighted in red, and an error message is displayed below the field.
4. Valid inputs are highlighted in green.
5. The "Add" button remains disabled until all fields contain valid data.
6. Upon successful submission, a new window appears confirming the registration.

## Customization
You can easily customize the form by modifying the following:
- Styling: Adjust the CSS styles in the code to change colors, fonts, etc.
- Validation rules: Modify the regex patterns in the validation methods to change the rules.
- Fields: Add or remove fields by updating the UI creation section and corresponding validation methods.

## Author
[Philippe-Je](https://github.com/Philippe-Je)