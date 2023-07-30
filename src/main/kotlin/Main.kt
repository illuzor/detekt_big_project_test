import java.io.File

fun main() {

    val bigProjectDir = File("").resolve("big_project")

    bigProjectDir
        .listFiles()
        .orEmpty()
        .filter { it.name.startsWith("module") }
        .forEach(File::deleteRecursively)

    createTestProject(bigProjectDir, 100)
}

private fun createTestProject(rootDir: File, modulesCount: Int) {
    repeat(modulesCount) {
        val moduleName = "module$it"
        val moduleDir = rootDir.resolve(moduleName)
        moduleDir.mkdir()
        createBuildDirectory(moduleDir, 5000)
    }
}

private fun createBuildDirectory(moduleDir: File, filesCount: Int) {
    val buildDir = moduleDir.resolve("build")
    buildDir.mkdir()
    repeat(filesCount) {
        val fileName = "file$it.txt"
        val file = buildDir.resolve(fileName)
        file.createNewFile()
    }
}

