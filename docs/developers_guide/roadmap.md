# Overview

Target Audience: **DDO Geeks and Gamers!**

This document outlines the general stages and direction of this project and should evolve as it assumes it's identity.
A more technical overview may follow in the future, which may be useful to potential contributors or the generally curious.

There are two simple but lofty goals to this project.

1. To enable comprehensive planning / analysis of character builds.
2. To create one or more clients that utilizes 1.
3. Perhaps even attract a new generation of DDO players.

When combined, you should be able to truly see what a given toon does.

-   Actual projected damage
    i.e. viewing your average damage on your equipped [Drow Rapier of the Weapon Master](https://ddowiki.com/page/Item:Drow_Rapier_of_the_Weapon_Master)
    not only understands the less-than-horrible 3[1d6] + 6 Pierce, Magic but
    also your Swashbuckling bonuses and that it's your deities favored weapon
    and calculates a _true_ human-readable min / max / average damage and
    probable crit percentage while raged against that Marut that keeps
    making you feel squishy.
-   Make better or at least more informed choices. Spend AP on another
    percent dodge, 3 AC, 1 PRR or affect blur?
-   Do I run for that named item or swap around and throw a random loot
    gen or craft something from Cannith to fill a slot?
-   Reverse build selection: Most people have a Legendary in mind, then
    start from level 1 trying to muddle through seeing what they end up with.
    Start by Adding how you want to end up.
    i.e. At level 30, I want a Power-striking, Bastard sword swinging, self-repairing automaton. So I start by selecting
    [Scion of Mechanus](https://ddowiki.com/page/Scion_of_Mechanus),
    [Power Attack](https://ddowiki.com/page/Power_Attack) and
    [Proficiency: Bastard Sword](https://ddowiki.com/page/Proficiency:_Bastard_Sword)
    and reverse-walk the pre-requisites to find a way to get there. Perhaps
    your level one toon is now a Half-orc because you didn't realise any race
    could self-repair with [Construct Essence](https://ddowiki.com/page/Construct_Essence)
    and then selected Artificer to get Bastard Sword as a Bonus Feat. Or you
    decided to go Purple Dragon Knight and go Charisma-based and just UMD
    [Tenser's Transformation](https://ddowiki.com/page/Tenser%27s_Transformation).
    Who knows? Tucows can rise again!

## Stages

The order of operations will be the Backbone API / core, followed by the clients. Although I would like to make the world's best character planner, I believe a comprehensive framework would be more beneficial to the DDO world at large.

### Backbone

Backbone should be a restful API. Ideally swagger / open API endpoints by OData internally using Apache Avro as a common serializer / storage. This should allow
Client options to develop third parties without the need to wait for an official client.

#### API

[OpenAPI (Swagger)](https://www.openapis.org/) / [OData](https://www.odata.org/)
This specification will drive development and serve as the public facing API with documentation.
Third parties can interact by viewing / downloading the swagger document and or querying the odata $metadata.

#### Core

Mostly internal, it contains object logic that contains meta-data such as stacking rules, damage etc.

#### Data

TBD: Data storage. A central store for core internal aspects as well as user-local data stores such as storing individual characters / builds etc.

#### Misc

Additional support / utility / ETL libraries

### Clients

Client apps and services that allow users to interact with the API in a more friendly fashion.

#### CLI (Command Line Interface)

a command line client will be likely be priority. This allows the more tech savvy to progress while also making intregration / regression and test client automation maintainable.

#### GUI

##### Web clients

Browser
A web client may be the next feasible client. While not the end goal, a browser based site with mobile in mind would allow the most widespread access to desktop (windows / Linux / Mac), mobile and tablet users.

#### Rich clients

The end goal of this project.

To keep a more consistent look and feel, a more universal client will be pursued. The client may add richer feature such as importing / exporting characters to other formats such as the commonly accepted forum post format and hopefully a format that will allow importing or exporting to other popular DDO Clients so users may try this without the need to start from scratch or feel locked into this tool.

#### Targets

-   Mobile / Tablet
    An Android client for mobile and or tablet.
-   Desktop
    Offers all functionality possible. Including concepts such as user input to help add information to the collective DDO datasphere.
