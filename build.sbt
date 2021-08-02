/*
 * Copyright 2019 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//=============================================================================
//============================== build details ================================
//=============================================================================

addCommandAlias("github-gen", "githubWorkflowGenerate")
addCommandAlias("github-check", "githubWorkflowCheck")
Global / onChangedBuildSource := ReloadOnSourceChanges

// format: off
val Scala213    = "2.13.6"
val Scala3      = "3.0.1"
// format: on

//=============================================================================
//============================ publishing details =============================
//=============================================================================

//see: https://github.com/xerial/sbt-sonatype#buildsbt
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

ThisBuild / baseVersion      := "0.5"
ThisBuild / organization     := "com.busymachines"
ThisBuild / organizationName := "BusyMachines"
ThisBuild / homepage         := Option(url("https://github.com/busymachines/pureharm-db-core"))

ThisBuild / scmInfo := Option(
  ScmInfo(
    browseUrl  = url("https://github.com/busymachines/pureharm-db-core"),
    connection = "git@github.com:busymachines/pureharm-db-core.git",
  )
)

/** I want my email. So I put this here. To reduce a few lines of code, the sbt-spiewak plugin generates this (except
  * email) from these two settings:
  * {{{
  * ThisBuild / publishFullName   := "Loránd Szakács"
  * ThisBuild / publishGithubUser := "lorandszakacs"
  * }}}
  */
ThisBuild / developers := List(
  Developer(
    id    = "lorandszakacs",
    name  = "Loránd Szakács",
    email = "lorand.szakacs@protonmail.com",
    url   = new java.net.URL("https://github.com/lorandszakacs"),
  )
)

ThisBuild / startYear  := Some(2019)
ThisBuild / licenses   := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

//until we get to 1.0.0, we keep strictSemVer false
ThisBuild / strictSemVer              := false
ThisBuild / spiewakCiReleaseSnapshots := false
ThisBuild / spiewakMainBranches       := List("main")
ThisBuild / Test / publishArtifact    := false

ThisBuild / scalaVersion       := Scala213
ThisBuild / crossScalaVersions := List(Scala213, Scala3)

//required for binary compat checks
ThisBuild / versionIntroduced := Map(
  Scala213 -> "0.1.0",
  Scala3   -> "0.5.0",
)

//=============================================================================
//================================ Dependencies ===============================
//=============================================================================
ThisBuild / resolvers += Resolver.sonatypeRepo("releases")
ThisBuild / resolvers += Resolver.sonatypeRepo("snapshots")

// format: off
val pureharmCoreV       = "0.3.0"     //https://github.com/busymachines/pureharm-core/releases
// format: on

//=============================================================================
//============================== Project details ==============================
//=============================================================================

lazy val root = project
  .in(file("."))
  .aggregate(
    `db-core`
  )
  .enablePlugins(NoPublishPlugin)
  .enablePlugins(SonatypeCiReleasePlugin)
  .settings(commonSettings)

lazy val `db-core` = project
  .settings(commonSettings)
  .settings(
    name := "pureharm-db-core",
    libraryDependencies ++= Seq(
      // format: off
      "com.busymachines"      %% "pureharm-core-anomaly"        % pureharmCoreV     withSources(),
      "com.busymachines"      %% "pureharm-core-sprout"         % pureharmCoreV     withSources(),
      // format: on
    ),
  )
  .settings(
    javaOptions ++= Seq("-source", "1.8", "-target", "1.8")
  )

//=============================================================================
//================================= Settings ==================================
//=============================================================================

lazy val commonSettings = Seq(
  scalacOptions ++= scalaCompilerOptions(scalaVersion.value)
)

def scalaCompilerOptions(scalaVersion: String): Seq[String] =
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, _)) =>
      Seq[String](
        //"-Xsource:3"
      )
    case _            => Seq.empty[String]
  }
