# GitHub Setup Guide

This document provides instructions for setting up GitHub authentication for the TrackPot project.

## Authentication Issue: Permission Denied (403)

If you're seeing an error like this:
```
remote: Permission to Cricsudheer/TrackPot.git denied to cric-sudheer.
fatal: unable to access 'https://github.com/Cricsudheer/TrackPot/': The requested URL returned error: 403
```

This means you're trying to push to a repository where your current GitHub credentials don't have write access.

## Solutions

### 1. Use the Correct GitHub Account

Make sure you're using the GitHub account that has write access to the repository.

#### Check your current Git configuration:
```bash
git config user.name
git config user.email
```

#### Update your Git configuration if needed:
```bash
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```

### 2. Use SSH Instead of HTTPS

SSH authentication is more secure and doesn't require you to enter your password each time.

#### Generate an SSH key (if you don't have one):
```bash
ssh-keygen -t ed25519 -C "your-email@example.com"
```

#### Add the SSH key to your GitHub account:
1. Copy your public key:
   ```bash
   # On Windows
   type C:\Users\YourUsername\.ssh\id_ed25519.pub | clip
   
   # On macOS
   pbcopy < ~/.ssh/id_ed25519.pub
   
   # On Linux
   xclip -sel clip < ~/.ssh/id_ed25519.pub
   ```
2. Go to GitHub → Settings → SSH and GPG keys → New SSH key
3. Paste your key and save

#### Change the repository remote URL from HTTPS to SSH:
```bash
# Check current remote
git remote -v

# Change to SSH
git remote set-url origin git@github.com:Cricsudheer/TrackPot.git
```

### 3. Use a Personal Access Token for HTTPS

If you prefer HTTPS, you can use a personal access token instead of your password.

1. Go to GitHub → Settings → Developer settings → Personal access tokens → Generate new token
2. Select the necessary scopes (at least `repo`)
3. Generate the token and copy it
4. Use this token as your password when pushing to GitHub

### 4. Request Access to the Repository

If you're not the owner of the repository and don't have write access:

1. Contact the repository owner (Cricsudheer) and request write access
2. They need to go to the repository → Settings → Collaborators → Add people

## Verifying Your Setup

After making changes, try pushing to the repository again:

```bash
git push origin main
```

If you still encounter issues, check the error message carefully for additional clues.