export function validateLoginFields(emailValue, passwordValue) {
    return emailValue?.length > 0 && passwordValue?.length > 0;
}