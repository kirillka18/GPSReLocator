# GPS ReLocator

Android-приложение для принудительной смены GPS-геолокации через Mock Location API.

**Автор:** Пронин К.Н.

---

## Сборка в Android Studio

### 1. Открыть проект

Откройте эту папку (`GPS ReLocator`) в **Android Studio**.  
Дождитесь завершения Gradle Sync — он автоматически скачает все зависимости.

### 2. API ключ не нужен!

Приложение использует **OSMDroid** с OpenStreetMap + CartoDB — полностью бесплатно, без регистрации.  
Карты кешируются локально после первой загрузки по интернету.

Доступные стили карт (переключаются в Настройках):
- 🌑 **Тёмная** — CartoDB Dark Matter (стиль по умолчанию)
- 🗺 **Стандарт** — OpenStreetMap
- 🛰 **Спутник** — Esri World Imagery

### 3. Gradle Wrapper (если нужно)

Если Android Studio жалуется на отсутствие `gradle-wrapper.jar`:
- Откройте **Tools → Gradle** и нажмите **Generate Gradle Wrapper**
- Или в терминале: `gradle wrapper --gradle-version 8.4`

### 4. Сборка

```
Build → Make Project (Ctrl+F9)
```

Для установки на устройство:
```
Run → Run 'app' (Shift+F10)
```

---

## Настройка на Samsung (перед использованием)

### 1. Включить режим разработчика

1. Настройки → О телефоне → Сведения о программном обеспечении
2. Нажать на **Номер сборки** 7 раз подряд
3. Появится сообщение "Режим разработчика активирован"

### 2. Назначить приложение как Mock Location provider

1. Настройки → Параметры разработчика
2. Найти: **"Выбрать приложение для имитации местоположения"** (или "Mock location app")
3. Выбрать: **GPS ReLocator**

### 3. Использование

1. Откройте приложение
2. Переместите карту в нужное место — перекрестие всегда в центре
3. Нажмите **«Релоцировать»**
4. GPS всех приложений на телефоне будет показывать выбранную локацию
5. Нажмите **«Отменить»** для возврата к реальной геолокации

---

## Структура проекта

```
app/
├── data/
│   ├── SavedPlace.kt          # Room entity
│   ├── SavedPlaceDao.kt       # DAO
│   └── AppDatabase.kt         # Room database
├── service/
│   └── MockLocationService.kt # Foreground service для мок-локации
├── ui/
│   ├── map/
│   │   ├── MapFragment.kt     # Главный экран с картой
│   │   └── MapViewModel.kt
│   ├── saved/
│   │   ├── SavedPlacesFragment.kt
│   │   ├── SavedPlacesViewModel.kt
│   │   └── SavedPlacesAdapter.kt
│   └── settings/
│       └── SettingsFragment.kt
├── utils/
│   ├── PrefsManager.kt        # SharedPreferences
│   └── Extensions.kt          # Проверка mock location permission
└── MainActivity.kt
```

---

## Требования

- Android 8.0+ (API 26+)
- Разрешение на доступ к геолокации
- Режим разработчика включён
- Приложение назначено как Mock Location app

---

## Технологии

- **Kotlin** + Coroutines
- **OSMDroid** + OpenStreetMap/CartoDB (тёмная тема, оффлайн кеш, без API ключа)
- **Room Database** (сохранённые места)
- **Navigation Component** (3 вкладки)
- **Material Design 3** (тёмная тема, жёлтый акцент)
- **FusedLocationProvider** (текущая геолокация)
- **Foreground Service** (фоновая работа мок-локации)
