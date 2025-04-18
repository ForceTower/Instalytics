# 📊 Instalytics

**Instalytics** is a sleek analytics platform for people who want deeper insights into their Instagram performance.  
Track your posts, compare yourself with others, and uncover what truly resonates with your audience.

> “Good content is art. Great content is data-driven.”

---

## 🚀 Features

- 📈 In-depth insights into your Instagram media  
- 🔍 Compare stats with other public accounts  
- 🔒 100% private — your data stays with us  
- 🧠 Metrics on engagement, reach, virality, and more  
- ⏱ Historical tracking to monitor growth over time

---

## 🧰 Tech Stack

- **iOS**: Swift (SwiftUI)
- **Android**: Kotlin (Jetpack Compose)
- **Shared Logic**: Kotlin Multiplatform (KMP)  
- **Backend**: Spring Framework with Kotlin  
- **Infrastructure**: Raspberry Pi / Mac Mini in a garage (because we like it indie)

---

## 🔧 Getting Started (for Devs)

> Note: This is a hybrid setup — native frontend, JVM backend, garage-tier infra.

### 🧩 Prerequisites

- Xcode & Android Studio installed  
- Java 21+  
- Kotlin 2
- Docker (optional, for DB)  
- Instagram Graph API access + valid token

### 🏗 Running the Backend

```bash
cd backend/
./gradlew bootRun
```

Make sure your `application.yml` or `.env` has the required Instagram credentials.

### 📱 Running the Mobile Apps

- iOS: Open `the project in Xcode, run on simulator or device  
- Android: Just run the project, sync, build, install

Shared KMP module must be linked correctly in both projects.

---

## 🔐 Privacy & Terms

- 📃 [Privacy Policy](./privacy_policy.md)  
- 📜 [Terms of Use](./terms_of_service.md)

---

## 🧪 Contributing

Pull requests are welcome! Open an issue or submit a PR if you have ideas, improvements, or bug fixes.

```bash
git checkout -b feature/awesome-feature
git commit -m "Add something awesome"
git push origin feature/awesome-feature
```

---

## 🧠 License

MIT — use it, remix it, make your own, just don’t sue us if it breaks.

---

## 💬 Support

Have questions or feedback? Email us at [your-email@example.com](mailto:your-email@example.com) or scream at your garage. (yes, this was AI generated)

---

Made with ❤️ and DIY energy for creators who care about metrics *and* vibes.
