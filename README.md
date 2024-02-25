# Multi Theme Based app

#### Your app supports multiple themes. Here are the four seed colors you can use:

- #2196F3 - Theme One.
- #F06292 - Theme Two.
- #ee9c4b - Theme Three.
- #7df37d - Theme Four.

## Step. 1

   ```xml
<!-- Theme Example-->
<style>
    <color name="one_seed">#2196F3</color>
    <color name="one_md_theme_light_primary">#0061A4</color>
    <color name="one_md_theme_light_onPrimary">#FFFFFF</color>
    <color name="......">#.......</color>
</style>
```

Declare your all colors of theme
in [colors.xml](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/res/values/colors.xml) file.

_To generate all color palettes of seed one by one then use [Material Theme Builder]()_

## Step. 2

```xml
<!--    Dialog Custom-->
<style name="ShapeDialog">
    <item name="cornerFamily">rounded</item>
    <item name="cornerSizeBottomLeft">16dp</item>
    <item name="cornerSizeBottomRight">16dp</item>
    <item name="cornerSizeTopLeft">16dp</item>
    <item name="cornerSizeTopRight">16dp</item>
</style>
```

Create your custom style
in  [styles.xml](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/res/values/styles.xml) file.

## Step. 3

Now in this step we will declare all color
in [themes.xml](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/res/values/themes.xml) file
for day and for night use
this [themes.xml](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/res/values-night/themes.xml)
file.

_Get all themes value of seed one by one then use [Material Theme Builder]()_

## Step. 4

To Handel and control theme which is store the value theme and its mode

- Set the theme value in this function

```kotlin
private fun setTheme(key: String, value: Int, context: Context) {
    val preferences: SharedPreferences = context.getSharedPreferences(
        "preferences",
        Context.MODE_PRIVATE
    )
    val editor = preferences.edit()
    editor.putInt(key, value)
    editor.apply()
}
```

- To get theme for app from `SharedPreferences`

```kotlin
private fun getTheme(context: Context, key: String): Int {
    val preferences: SharedPreferences = context.getSharedPreferences(
        "preferences",
        Context.MODE_PRIVATE
    )
    return preferences.getInt(key, 0)
}

```

get full code
on [Themes.kt](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/java/com/example/themedialog/Themes.kt)

# Step. 5

Create ui for theme
controller
```kotlin
    private fun createDialog() {
        val colorArray = listOf(
            R.color.one_seed,
            R.color.two_seed,
            R.color.analogous_two_seed,
            R.color.triadic_seed,
        )

        colorAdapter = ColorAdapter(context, colorArray, Themes().getThemePosition(context)) {
            colorTheme = it
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = colorAdapter
        }

        binding.toggleButton.check(
            when (Themes().getModePosition(context)) {
                0 -> R.id.buttonSystem
                1 -> R.id.buttonDay
                2 -> R.id.buttonNight
                else -> R.id.buttonSystem
            }
        )
        binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.buttonSystem -> {
                        modeTheme = 0
                    }

                    R.id.buttonDay -> {
                        modeTheme = 1
                    }

                    R.id.buttonNight -> {
                        modeTheme = 2
                    }
                }
            }
        }

        builder = MaterialAlertDialogBuilder(context).setView(binding.root)
        builder.setTitle(R.string.choose_theme)
            .setIcon(R.drawable.ic_round_brush)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(context.getText(R.string.ok)) { dialog, which ->
                themeAction(colorTheme)
                modeAction(modeTheme)
            }
    }

```
to get full code [ThemeDialogBuilder.kt](https://github.com/TanotiCoder/MultiTheme/blob/master/themedialog/src/main/java/com/example/themedialog/ThemeDialogBuilder.kt)
