Specification Heading
=====================

|iteration|
|1|
|2|
|3|
|4|
|5|
     
Navigate To Login Screen Test
---------------------------------------------------------------------
//* Iteration <iteration> başlatıldı
* Workplace url ekranının elementlerini bekle
* Connect butonunun enabled özelliğinin false olduğu kontrol et
* Geçerli Workspace URL gir
* Connect butonunun enabled özelliğinin true olduğunu kontrol et
* Connect butonuna tıkla
* Authentication Selection ekranının açılması bekle
* Authentication Selection ekranının açıldığı kontrol et
* Login butonuna tıkla
* Login ekranının açılmasını bekle
* Login ekranının açıldığını kontrol et

Navigate To SignUp Screen Test
---------------------------------------------------------------------
* Iteration <iteration> başlatıldı
* Workplace url ekranının elementlerini bekle
* Connect butonunun enabled özelliğinin false olduğu kontrol et
* Geçerli Workspace URL gir
* Connect butonunun enabled özelliğinin true olduğunu kontrol et
* Connect butonuna tıkla
* Authentication Selection ekranının açılması bekle
* Authentication Selection ekranının açıldığı kontrol et
* Create anaccount butonuna tıkla
* Sign Up ekranının açılmasını bekle
* Sign Up ekranının açıldığını kontrol et



2. NavigateToSignUpScreenTest
Amaç: Kullanıcı Sign Up ekranına gidebilmeli.
Başlangıç ekranı: AuthenticationSelectionScreen
Adımlar:
    1. Wait for AuthenticationSelectionScreen elements
    2. Click “Sign Up” button
    3. Verify SignUpScreen is displayed