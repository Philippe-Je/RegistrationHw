package org.example.registrationhw;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.regex.Pattern;

/**
 * A JavaFX application that creates a styled registration form with input validation.
 * The form includes fields for first name, last name, email, date of birth, and zip code.
 * It provides real-time validation feedback and enables the submit button only when all inputs are valid.
 */
public class StyledRegistrationForm extends Application {
    private Button addButton;

    /**
     * The main entry point for the JavaFX application.
     * Sets up the user interface, including all form fields, validation, and styling.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");

        // Load the image
        Image logo = new Image(getClass().getResourceAsStream("/user.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        // Create title label
        Label titleLabel = new Label("Registration Form");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: linear-gradient(to right, #00c6ff, #0072ff);");

        // Create UI components
        TextField firstNameField = createStyledTextField("First Name");
        TextField lastNameField = createStyledTextField("Last Name");
        TextField emailField = createStyledTextField("Email Address");
        TextField dobField = createStyledTextField("MM/DD/YYYY");
        TextField zipCodeField = createStyledTextField("Zip Code");

        addButton = new Button("Add");
        addButton.setDisable(true);

        // Style components
        addButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-padding: 10px 20px;"
        );

        // Layout
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(30));
        layout.getChildren().addAll(
                logoView,
                titleLabel,
                firstNameField,
                createValidationLabel(),
                lastNameField,
                createValidationLabel(),
                emailField,
                createValidationLabel(),
                dobField,
                createValidationLabel(),
                zipCodeField,
                createValidationLabel(),
                addButton
        );

        layout.setStyle("-fx-background-color: white; -fx-padding: 30px; -fx-alignment: center;");

        // Add validation listeners
        addValidationListener(firstNameField, this::validateName);
        addValidationListener(lastNameField, this::validateName);
        addValidationListener(emailField, this::validateEmail);
        addValidationListener(dobField, this::validateDOB);
        addValidationListener(zipCodeField, this::validateZipCode);

        // Add action for the Add button
        addButton.setOnAction(e -> navigateToSuccessUI());

        Scene scene = new Scene(layout, 350, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a styled TextField with the given prompt text.
     *
     * @param promptText The prompt text to display in the TextField.
     * @return A styled TextField.
     */
    private TextField createStyledTextField(String promptText) {
        TextField field = new TextField();
        field.setPromptText(promptText);
        field.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: lightblue; -fx-background-color: transparent;");
        return field;
    }

    /**
     * Creates a Label for displaying validation messages.
     *
     * @return A styled Label for validation messages.
     */
    private Label createValidationLabel() {
        Label label = new Label();
        label.setStyle("-fx-text-fill: red;");
        return label;
    }

    /**
     * Adds a validation listener to the given TextField.
     * The listener updates the field's style and validation message based on the input's validity.
     *
     * @param field The TextField to add the listener to.
     * @param validator The validation function to use.
     */
    private void addValidationListener(TextField field, ValidationFunction validator) {
        VBox parent = (VBox) field.getParent();
        int fieldIndex = parent.getChildren().indexOf(field);
        Label validationLabel = (Label) parent.getChildren().get(fieldIndex + 1);

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isValid = validator.validate(newValue);
            updateFieldValidationState(field, validationLabel, isValid);
            updateAddButtonState();
        });

        field.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                field.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: lightblue; -fx-background-color: transparent;");
                validationLabel.setText("");
            }
        });
    }

    /**
     * Updates the visual state of a field based on its validation state.
     *
     * @param field The TextField to update.
     * @param validationLabel The Label to display validation messages.
     * @param isValid Whether the field's current value is valid.
     */
    private void updateFieldValidationState(TextField field, Label validationLabel, boolean isValid) {
        if (isValid) {
            field.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: green; -fx-background-color: transparent;");
            validationLabel.setText("");
        } else {
            field.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: red; -fx-background-color: transparent;");
            validationLabel.setText(getValidationMessage(field.getPromptText()));
        }
    }

    /**
     * Validates a name input.
     *
     * @param name The name to validate.
     * @return true if the name is valid, false otherwise.
     */
    private boolean validateName(String name) {
        return Pattern.matches("^[A-Za-z]{2,25}$", name);
    }

    /**
     * Validates an email input.
     *
     * @param email The email to validate.
     * @return true if the email is valid, false otherwise.
     */
    private boolean validateEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@farmingdale\\.edu$", email);
    }

    /**
     * Validates a date of birth input.
     *
     * @param dob The date of birth to validate.
     * @return true if the date of birth is valid, false otherwise.
     */
    private boolean validateDOB(String dob) {
        return Pattern.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$", dob);
    }

    /**
     * Validates a zip code input.
     *
     * @param zipCode The zip code to validate.
     * @return true if the zip code is valid, false otherwise.
     */
    private boolean validateZipCode(String zipCode) {
        return Pattern.matches("^\\d{5}$", zipCode);
    }

    /**
     * Updates the state of the Add button based on the validity of all fields.
     */
    private void updateAddButtonState() {
        boolean allValid = true;
        for (javafx.scene.Node node : ((VBox) addButton.getParent()).getChildren()) {
            if (node instanceof TextField && !((TextField) node).getStyle().contains("-fx-border-color: green;")) {
                allValid = false;
                break;
            }
        }
        addButton.setDisable(!allValid);
    }

    /**
     * Gets the appropriate validation message for a given field.
     *
     * @param fieldName The name of the field.
     * @return The validation message for the field.
     */
    private String getValidationMessage(String fieldName) {
        switch (fieldName) {
            case "First Name":
            case "Last Name":
                return "Must be 2-25 characters long.";
            case "Email Address":
                return "Must be a valid Farmingdale email address.";
            case "MM/DD/YYYY":
                return "Must be a valid date in MM/DD/YYYY format.";
            case "Zip Code":
                return "Must be a 5-digit number.";
            default:
                return "Invalid input.";
        }
    }

    /**
     * Navigates to the success UI after successful form submission.
     */
    private void navigateToSuccessUI() {
        Stage successStage = new Stage();
        successStage.setTitle("Registration Successful");

        Label successLabel = new Label("Registration Successful!");
        successLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        successLabel.setStyle("-fx-text-fill: green;");

        VBox successLayout = new VBox(20);
        successLayout.setAlignment(Pos.CENTER);
        successLayout.getChildren().add(successLabel);

        Scene successScene = new Scene(successLayout, 300, 200);
        successStage.setScene(successScene);
        successStage.show();
    }

    /**
     * Functional interface for validation functions.
     */
    @FunctionalInterface
    private interface ValidationFunction {
        /**
         * Validates the given input.
         *
         * @param input The input to validate.
         * @return true if the input is valid, false otherwise.
         */
        boolean validate(String input);
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}