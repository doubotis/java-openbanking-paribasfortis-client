# java-openbanking-paribasfortis-client
 Java Client for Openbanking Paribas Fortis
 
 ![image](https://developer.bnpparibasfortis.com/assets/images/e8c5adfa32e7c83e0ce15596fa96e4a9.svg)
 
 More information on https://developer.bnpparibasfortis.com/

Sandbox usage only for the moment.

## Usage

Set the parameters needed :
- Client ID
- Client Secret
- Organization ID
- Redirect URI

You can get them from the developer website (linked above).

The client is able to obtain a new token when the old is deprecated.
To allow the system to work, you must set Organization IDs and Redirect URI even for APIs client that doen't need them directly, to allow the AuthClient to get new tokens automatically.

### AuthClient

AuthClient are used to get new tokens from code or refresh tokens.

### AISPClient

AISPClient must be used for account information of BNP Paribas Fortis accounts.
