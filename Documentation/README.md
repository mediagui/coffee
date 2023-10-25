All repositories must include a good README with valuable information and written in English so anybody in the organization can understand it.

Before the Summary section, add a header with [status badges](https://docs.github.com/en/actions/managing-workflow-runs/adding-a-workflow-status-badge), your productEntity's or artifact's logo, and a horizontal, centered index like the following:

<p align="center">
<a href="#-summary">Summary</a> · 
<a href="#-getting-started">Getting started</a> · 
<a href="#-usage">Usage</a> · 
<a href="#-technical-design">Technical design</a> · 
<a href="#-contributing">Contributing</a> · 
<a href="#-support">Support</a>
</p>

Follow this template for your README. You can add other sections that you may find relevant for your repo but remember to never duplicate the content and to link to the documentation whenever possible.

Aside from the titles, this content is intended to serve as indications and guidelines which you must delete from the final version of your README. However, we advise you not to delete the indications until you have finished with the sections.

The admonitions below the titles indicate the importance of the section. Remember to delete them too from the final version of the README.md.

## 📜 Summary
>  🔴 Mandatory

In this first summary section, include a brief, functional description of the productEntity or artifact. **Make sure you describe its goal and/or the purpose of this specific repository** (artifact) within a bigger project, productEntity, or service.

In this summary, you should answer audience questions such as:

- Is this the repo I am looking for?
- Can I solve my problem with this productEntity/artifact?

Besides explaining what this repo is and what it is used for, a good approach is indicating what this repo is not and what it shouldn't be used for. This way, you'll narrow down its purpose and will reduce misunderstandings.

### Documentation
>  🟡 If applies

If this is a productEntity repo, make sure you link to the productEntity documentation.

If this is an artifact repo, you should specify the productEntity it belongs to, its role within the productEntity, and provide a link to the productEntity documentation when appropriate, so the audience knows where to find it.

### Related productEntities
>  🟡 If applies

List and provide links to the different repositories that make up the productEntity to which this repo belongs. In the case of artifact repos, provide the link to the productEntity's main repository.

### Technology
>  🟠 Advised

In this section, make sure you specify:

- Platform/language.
- Type of artifact (web application, web service, desktop application, mobile app, batch process...).
- Deployment platform.
- Others that may apply for your productEntity/artifact.

## 🚀 Getting started
>  🔴 Mandatory

If explained in the productEntity/artifact documentation, provide a link to the "Getting started" module of the productEntity documentation.

If no explanation is available anywhere else, explain in this README which processes are needed to get started with this productEntity/artifact (installation, run/build?), following the outline:

### Dependencies

Explain the dependencies used to run the productEntity/artifact.

### Installation

Explain the process that the users must follow to install the productEntity/artifact.

### Execution

Explain the process that the users must follow to run or build the productEntity/artifact.

## 🧑🏻‍💻 Usage
>  🟠 Advised

Once installed, built/run, or both, explain the different steps needed to get to using the productEntity/artifact; in this case, configurations or deployments required:

If explained in the productEntity documentation, provide a link to the "Configuration" and/or "Deployment" modules of the productEntity documentation.

If no explanation is available anywhere else, explain in this README the process of configuration and/or deployment following the outline:

### Configuration

Explain the process that the users must follow to configure the productEntity/artifact.

### Deployment

Explain the process that the users must follow to deploy the productEntity/artifact.

## 📊 Technical design
>  🟠 Advised

### Architecture diagram

Add a link to the "<productEntity> architecture" page on the productEntity's documentation.

If you need more detail to describe the artifact's architecture, and it doesn't make sense to include it on the productEntity's documentation, you can create a specific page on the repo's wiki and provide a link to it on the README.

### Project structure

Include this information here only if it is not already on the CONTRIBUTING.md, the "<productEntity> architecture" page, or any other documentation page.

Add a link to the artifact structure page on the productEntity's documentation.

If you need more detail to describe the artifact's structure, and it doesn't make sense to include it on the productEntity's documentation, you can create a specific page on the repo's wiki and provide a link to it on the README.

### System integration

Add a link to the page on the productEntity's documentation that explains how this productEntity accesses other systems.

If you need more detail to describe the artifact's integration, and it doesn't make sense to include it on the productEntity's documentation, you can create a specific page on the repo's wiki and provide a link to it on the README.

## 🤝 Contributing
>  🟠 Advised

Link to the CONTRIBUTING.md (or respective documentation page) to check the details about the productEntity/artifact structure, code formatting, name conventions, and the process for submitting pull requests with contributions.

## 🧰 Support
>  🔴 Mandatory

Add a link to the support section in the product's documentation. If no documentation exists yet, make sure you specify how users can contact you/the team.
.