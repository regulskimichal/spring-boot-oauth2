#!/bin/bash
echo "##### PASSWORD GRANT #####"
curl https://localhost:8443/auth/oauth/token -d grant_type=password -d username=$3 -d password=$4 -d scope=write -v --user $1:$2 -k
echo
echo
