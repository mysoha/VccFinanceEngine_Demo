# Welcome to VccFinance Engine!
- [Simple project demo](https://github.com/mysoha/VccFinanceEngine_Demo.git)

# Requirement
- minSdk: 23
- Gradle: 7.3.1

# Hướng dẫn import SDK vào trong dự án
- Do bên SDK sử dụng JFlog nên bên tích hợp cần import thêm đường dẫn url vào file **build.gradle (Project)**
    ```
    allprojects {
        repositories {
            google()
            mavenCentral()
            maven {
                url "https://sohames.jfrog.io/artifactory/vcc-sdk-finance-gradle-dev-local/"
            }
        }
    }
    ```

- Import phiên bản SDK vào file **build.gradle (Module)** để có thể sử dụng
    ```
    dependencies {
        implementation 'vcc.viv.vccfinance:vcc-sdk-vccfinance:1.0.0-dev3'
    }
    ```
  Version hiện tại đang là "1.0.0-dev3". Liên hệ đội Android core để biết phiên bản mới nhất.
# Sử dụng
- Khởi tạo một instance của class **VccFinanceEngine**

    ```
    VccFinanceEngine vccFinanceEngine = VccFinanceEngine.getInstance();
    ```
- Gọi các hàm trong class này để sử dụng SDK
  ***SDK hiện hỗ trợ activity, view và fragment***

  **Start activity**
    ```
    vccFinanceEngine.startVccFinanceActivity(context);
    ```

  **Trả ra view**
    ```
    View vccFinanceView = vccFinanceEngine.getVccFinanceView(context);
    ```

  **Trả ra fragment**
    ```
    Fragment vccFinanceFragment = vccFinanceEngine.getVccFinanceFragment(context);
    ```

  Ngoài ra trong trường hợp cần custom view thì có thể tự sử dụng thêm một số hàm tính toán được mở trong class **VccFinanceEngine**

# Settings (dành cho team SDK)
- Thiết lập thông tin trên dự án :
  - Mở file gradle.properties ( download ở bước trước )
  - Copy các thông tin ( user trên jfrog) vào trong gradle.properties của dự án
  - Mở file snippet ( download ở bước trước )
  - Copy classpath vào buildscript > dependencies.
  - Nên sử dụng classpath lastest thay thế cho classpath được generate ra
  ```
  classpath "org.jfrog.buildinfo:build-info-extractor-gradle:latest.release"
  ```
  - Copy "**allprojects{}**" vào cuối file build.gradle ngoài cùng nếu chưa có
  - Copy đoạn bên dưới vào vào trong allprojects. ( maven-publish dùng để tránh lỗi "**Could
    not find method publishing()...**" nếu gặp )
  ```
  apply plugin: 'com.jfrog.artifactory'
  apply plugin: 'maven-publish'
  ```
  - Copy artifactory vào cuối file build.gradle ngoài cùng, xóa đi mục ***resolve*** nếu có
  - Copy thông thông tin dự án vào build.gradle tổng của dự án
  ```
  def libraryGroupId = 'packages dự án'
  def libraryArtifactId = 'tên của dự án'
  def libraryVersion = 'version của dự án'
  ```
  - Copy script để publish lên jfrog
  ```
  project('tên module') {
  artifactoryPublish.dependsOn('build')

               publishing {
                   publications {
                       aar(MavenPublication) {
                           groupId = libraryGroupId
                           artifactId = libraryArtifactId
                           version = libraryVersion
                           // Tell maven to prepare the generated "*.aar" file for publishing
                           artifact("$buildDir/outputs/aar/${project.getName()}-release.aar")

                           pom.withXml {
                               def dependenciesNode = asNode().appendNode('dependencies')
                               configurations.implementation.allDependencies.each { dependency ->
                                   def dependencyNode = dependenciesNode.appendNode('dependency')
                                   dependencyNode.appendNode('groupId', dependency.group)
                                   dependencyNode.appendNode('artifactId', dependency.name)
                                   dependencyNode.appendNode('version', dependency.version)
                               }
                           }
                       }
                   }
               }

               artifactoryPublish {
                   publications(publishing.publications.aar)
               }
           }
    ```
- Upload :
  - Update version của bản build
  - Clean project
  - Bật terminal tại root folder của dự án
  - Chạy lệnh
    ```
    gradlew clean artifactoryPublish
    ```

# Member
Team Android Core **VcCorp**
- Hoang Trung Dung
- Bach Dang Dung