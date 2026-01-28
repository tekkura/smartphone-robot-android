import com.android.build.api.dsl.ApplicationExtension
import jp.oist.abcvlib.buildTools
import jp.oist.abcvlib.compileSdk
import jp.oist.abcvlib.configureBuildTypes
import jp.oist.abcvlib.configureKotlinAndroid
import jp.oist.abcvlib.minSdk
import jp.oist.abcvlib.plugin
import jp.oist.abcvlib.targetSdk
import jp.oist.abcvlib.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(versionCatalog.plugin("android-application"))
            pluginManager.apply(versionCatalog.plugin("kotlin-android"))

            extensions.configure<ApplicationExtension> {
                compileSdk = versionCatalog.compileSdk
                buildToolsVersion = versionCatalog.buildTools

                defaultConfig{
                    targetSdk = versionCatalog.targetSdk
                    minSdk = versionCatalog.minSdk
                }

                configureKotlinAndroid(this)
                configureBuildTypes(this)
            }
        }
    }
}