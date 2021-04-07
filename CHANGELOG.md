# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

# unreleased

# 0.3.0

### dependency upgrades

- [pureharm-config](https://github.com/busymachines/pureharm-config/releases) `0.3.0`

# 0.2.0

## deprecations

- deprecate `DBConnectionConfig` companion object. Use [pureharm-config-ciris](https://github.com/busymachines/pureharm-config-ciris), or some other config library of your choice to read these values on your own terms.
- deprecate all methods in Repo that take multiple elements. Will be removed since API is extremely clunky, and will be pushed down to be DB specific.

### dependency upgrades

- [pureharm-core-anomaly](https://github.com/busymachines/pureharm-core/releases) `0.2.0`
- [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.2.0`
- [pureharm-config](https://github.com/busymachines/pureharm-config/releases) `0.2.0`

# 0.1.0

Split out from [pureharm](https://github.com/busymachines/pureharm) as of version `0.0.7`.

- cross compiled to Scala 2.13 -- pending support for scala 3.0.0-RC1
- add `DBPort` to `DBConnectionConfig`

:warning: Removed deprecated methods :warning:

- removed deprecated `DAOAlgebra` traits
