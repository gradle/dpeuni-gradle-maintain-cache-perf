# DPE University Training

<p align="left">
<img width="10%" height="10%" src="https://user-images.githubusercontent.com/120980/174325546-8558160b-7f16-42cb-af0f-511849f22ebc.png">
</p>

## Maintaining Gradle Build Cache Performance Exercise

This is a hands-on exercise to go along with the
[Maintaining Optimal Gradle Build Cache Performance](https://dpeuniversity.gradle.com/app/courses/4fcbecbc-7cff-449a-a509-07cf70403f0c)
training module. In this exercise you will go over the following:

* Using the Develocity Build Validation Scripts to identify build caching issues

---
## Prerequisites

* Finished going through the relevant sections in the training course

---
## Clone this Repository

This repository contains the project with build caching issues. We will use the
Develocity Build Validation Scripts to identify these issues and address them.

1. Clone this repository in a new directory location on your machine

In the rest of this README the location will be referred to as `/sample-project`.
Whenever you see `/sample-project`, replace it with the location you are using.

---
## Develocity Authentication

We will use the DPE University Develocity instance as the remote cache.
If you haven't already done so, you can authenticate with the Develocity service by running:

```shell
./gradlew provisionGradleEnterpriseAccessKey
```

The output of the task will indicate a browser window will come up from which you can complete the authentication:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/ccafa270-dbab-4c66-ba12-caabcd10399c">
</p>

Once the browser window comes up you can enter a title for the access key that will be created or go with the suggested title:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/1aeef46a-2fb6-472a-8d87-82af31b20799">
</p>

Once confirmed you will see the following message and you can close the browser window and return to the editor:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/1711c9db-814c-4df1-9d18-42fe5d1b82f8">
</p>

---
## Downloading the Develocity Build Validation Scripts

1. Open the [Develocity Build Validation Scripts installation instructions](https://github.com/gradle/gradle-enterprise-build-validation-scripts/blob/main/Gradle.md#installation)

2. Copy the installation command

3. Open a terminal, create a directory for the installation location and run the command there

4. Go to the directory containing the Develocity Build Validation Scripts, you should see contents similar to:

```bash
$ ls -ltr
total 192
-rw-r--r--   1 adayal  wheel    862 Oct 25  2023 network.settings
-rw-r--r--   1 adayal  wheel    438 Oct 25  2023 mapping.example
drwxr-xr-x  27 adayal  wheel    864 Oct 25  2023 lib
-rw-r--r--   1 adayal  wheel      6 Oct 25  2023 VERSION
-rw-r--r--   1 adayal  wheel    404 Oct 25  2023 README.md
-rw-r--r--   1 adayal  wheel  11356 Oct 25  2023 LICENSE
-rwxr-xr-x   1 adayal  wheel  16628 Oct 25  2023 05-validate-remote-build-caching-ci-local.sh
-rwxr-xr-x   1 adayal  wheel  10954 Oct 25  2023 04-validate-remote-build-caching-ci-ci.sh
-rwxr-xr-x   1 adayal  wheel  11958 Oct 25  2023 03-validate-local-build-caching-different-locations.sh
-rwxr-xr-x   1 adayal  wheel  10874 Oct 25  2023 02-validate-local-build-caching-same-location.sh
-rwxr-xr-x   1 adayal  wheel  11336 Oct 25  2023 01-validate-incremental-building.sh
```

---
## Identify Local Caching Issues using Same Location

Let's run experiment 02 to identify local caching issues by running commands in the same location.

1. Go to the directory where the Develocity Build Validation Scripts are installed

2. Run the script in interactive mode: `./02-validate-local-build-caching-same-location.sh -i`

3. Press the `<enter>` key to start the experiment as the following prompt indicates:

```bash
Press <Enter> to get started with the experiment.
```

4. The project does not use the Common Custom User Data Gradle plugin, and we will proceed without it.
   Press the `<enter>` key when you see the following prompt:

```bash
Press <Enter> once you have (optionally) configured your build with the Common Custom User Data Gradle plugin and pushed the changes.
```

5. You should have already authenticated with the Develocity service, so press
   the `<enter>` key when you see the following prompt:

```bash
Press <Enter> once you have (optionally) adjusted your access permissions and configured the API credentials on your machine.
```

6. Enter the file path to the project using the `file://` URL. Replace `/sample-project` with the actual location:

```bash
What is the URL for the Git repository that contains the project to validate? file:///sample-project
```

7. For the next 4 questions use the default values by pressing the `<enter>` key without typing anything else:

```bash
What is the branch for the Git repository that contains the project to validate? <the repository's default branch>
What is the commit id for the Git repository that contains the project to validate? <the branch's head>
What are additional options to use when cloning the Git repository? --depth=1
What is the directory to invoke the build from? <the repository's root directory>
```

8. When the script asks what task to invoke, specify the `build` task:

```bash
What are the Gradle tasks to invoke? build
```

9. For the last question, press `<enter>` without typing anything else:

```bash
What are additional cmd line arguments to pass to the build invocation? <none>
```

10. When you see the following prompt, press `<enter>`:

```bash
Press <Enter> to check out the project from Git.
```

It will create a separate clone of the repository for the experiment.

**NOTE**: If you get an error here, check the file URL of the repository.

11. When it prompts to run the first build press `<enter>`:

```bash
Press <Enter> to run the first build of the experiment.
```

It will run the `build` task on the project.

12. When it prompts to run the second build press `<enter>`:

```bash
Press <Enter> to run the second build of the experiment.
```

It will run the `clean` task followed by the `build` task on the project.

13. After it has finished, it will prompt you to continue and look at the results. Press the `<enter>` key:

```bash
Press <Enter> to measure the build results.
```

In the `Performance Characteristics` section, it will inform you there is one
task with a potential build caching issue.

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/ddac4847-f372-4a61-a45a-4f36854fd273">
</p>

14. Open the `Executed cacheable tasks` link.
    It will inform you the `:app:test` task was executed even though it should have
    gotten it's output from the cache.

<p align="center">
<img width="60%" height="60%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/94ccf586-8fdf-4e60-a66f-02375be73ae7">
</p>

15. Open the `Task inputs comparison` link.
    It will inform you that the `model-1.0.jar` file from the `model` subproject changed.
    This is an input to the `:app:test` task.

<p align="center">
<img width="60%" height="60%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/afc50f82-ed0a-42b2-9a92-222d3ef4d567">
</p>

16. Clear the filters on the top right of the page

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/3b17a82f-bc89-4197-9328-abed484e0faf">
</p>

17. Expand the `jar` task on the `model` subproject. You will see the `model/build/tmp/jar/MANIFEST.MF` file
    which is an input to this task changed.

<p align="center">
<img width="60%" height="60%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/77b5f77d-aaeb-40bd-bfad-625a099beda8">
</p>

18. In the terminal, notice the `Experiment artifact dir` in the `Summary` section:

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/5bbf4ce8-c945-4f5d-93c2-c7ad23dd329c">
</p>

All the files for the two builds are in this directory.

19. Run a `diff` to see how the `model/build/tmp/jar/MANIFEST.MF` file changed between the builds:

**NOTE**: Adjust the path for the diff as per your `Experiment artifact dir`.

```bash
$ diff .data/02-validate-local-build-caching-same-location/20240517T073834-66475dda/first*/model/build/tmp/jar/MANIFEST.MF .data/02-validate-local-build-caching-same-location/20240517T073834-66475dda/second*/model/build/tmp/jar/MANIFEST.MF
2c2
< Implementation-Timestamp: 2024-05-17T13:49:25.219892Z
---
> Implementation-Timestamp: 2024-05-17T13:50:52.688382Z
```

We can see a timestamp is changing in the file. You may recognize this issue from a previous training.

20. We can use input normalization to ignore the timestamp attribute. Open `app/build.gradle.kts` and add the following:

```kotlin
normalization {
    runtimeClasspath {
        metaInf {
            ignoreAttribute("Implementation-Timestamp")
        }
    }
}
```

21. Commit the change to the local repository:

```bash
$ git commit -am "Use input normalization to ignore timestamp attribute in manifest file"
```

22. Run the experiment again. At the end of the output you will see a command you can run to do this:

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/d28bb3d8-5df9-4df7-98d5-75ba90ef3ff4">
</p>

You should see no issues found this time.

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/c0437f21-d33c-417e-91e6-492d9aee77fe">
</p>

---
## Identify Local Caching Issues using Different Locations

Each experiment can identify different kinds of build caching issues.
Using experiment 02 we identified and fixed one issue. Now let's run the next
experiment and see if it identifies any other issues.

1. Go to the directory where the Develocity Build Validation Scripts are installed

2. You can run the 03 script in interactive mode, however since it is similar to
   the 02 script, we can run it using the command as follows:

**NOTE**: Replace `/sample-project` with the location of the project.

```bash
./03-validate-local-build-caching-different-locations.sh -r file:///sample-project -t build
```

The experiment will run and find 1 task with a potential build caching issue:

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/aadda3c5-a246-4da5-ae9e-d5e0cfc5d6d3">
</p>

3. Open the `Executed cacheable tasks` link.
    It will inform you the `:app:countSrc` task was executed even though it should have
    gotten it's output from the cache.

<p align="center">
<img width="60%" height="60%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/6e317c10-fed5-4c0a-ac54-ba6f37a1a838">
</p>

4. Open the `Task inputs comparison` link.
    It will inform you that the `app/src` input for the task has different absolute paths.

<p align="center">
<img width="60%" height="60%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/537e36e9-04c4-4bd5-af8e-16bb459ba3a1">
</p>

That tells us that instead of using relative paths, this input has it's
`path sensitivity` set to absolute. This experiment runs the two builds in
different directories, so the absolute paths would have been different for
this input.

Let us confirm this is the issue.

5. Open `app/build.gradle.kts` and go to the `countSrc` task configuration.
   You will see the `srcDir` input has been set to use absolute `path sensitivity`

<p align="center">
<img width="80%" height="80%" src="https://github.com/gradle/dpeuni-gradle-intro-devs-init/assets/120980/4f6780ac-8c77-44c3-922e-0fadf2ae64ae">
</p>

When you encounter this, you can check with other engineers on the project whether this input should be using relative `path sensitivity`.
Let's assume you checked and it should use relative.

6. Change the `path sensitivity` to relative.

7. Commit the change to the local repository:

```bash
$ git commit -am "countSrc task's srcDir input should have relative path sensitivity"
```

8. Run the experiment again:

**NOTE**: Replace `/sample-project` with the location of the project.

```bash
./03-validate-local-build-caching-different-locations.sh -r file:///sample-project -t build
```

You should no longer see any issues.

9. In a real project, you can now create PRs for these fixes.
